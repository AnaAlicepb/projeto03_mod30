package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.ProdutoQuantidade;
import domain.Venda;
import domain.Venda.Status;
import dao.factory.ProdutoQuantidadeFactory;
import dao.factory.VendaFactory;
import dao.generic.GenericDAO;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO {

    @Override
    public Class<Venda> getTipoClasse() {
        return Venda.class;
    }

    @Override
    public void atualizarDados(Venda entity, Venda entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setStatus(entity.getStatus());
    }

    @Override
    public void excluir(String valor) {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    public void finalizarVenda(Venda venda) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE TB_VENDA SET STATUS_VENDA = ? WHERE ID = ?";
            connection = getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, Status.CONCLUIDA.name());
            stm.setLong(2, venda.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("ERRO ATUALIZANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    protected String getQueryInsercao() {
        return "INSERT INTO TB_VENDA (ID, CODIGO, ID_CLIENTE_FK, VALOR_TOTAL, DATA_VENDA, STATUS_VENDA) VALUES (nextval('sq_venda'),?,?,?,?,?)";
    }

    @Override
    protected String getQueryExclusao() {
        throw new UnsupportedOperationException("Operação não permitida");
    }

    @Override
    protected String getQuerytAtualizacao() {
        return "UPDATE TB_VENDA SET CODIGO = ?, ID_CLIENTE_FK = ?, VALOR_TOTAL = ?, DATA_VENDA = ?, STATUS_VENDA = ? WHERE ID = ?";
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Venda entity) throws SQLException {
        stmInsert.setString(1, entity.getCodigo());
        stmInsert.setLong(2, entity.getCliente().getId());
        stmInsert.setBigDecimal(3, entity.getValorTotal());
        stmInsert.setTimestamp(4, Timestamp.from(entity.getDataVenda()));
        stmInsert.setString(5, entity.getStatus().name());
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, String valor) throws SQLException {
        throw new UnsupportedOperationException("Operação não permitida");
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Venda entity) throws SQLException {
        stmUpdate.setString(1, entity.getCodigo());
        stmUpdate.setLong(2, entity.getCliente().getId());
        stmUpdate.setBigDecimal(3, entity.getValorTotal());
        stmUpdate.setTimestamp(4, Timestamp.from(entity.getDataVenda()));
        stmUpdate.setString(5, entity.getStatus().name());
        stmUpdate.setLong(6, entity.getId());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmSelect, String valor) throws SQLException {
        stmSelect.setString(1, valor);
    }

    private StringBuilder sqlBaseSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT V.ID AS ID_VENDA, V.CODIGO, V.VALOR_TOTAL, V.DATA_VENDA, V.STATUS_VENDA, ");
        sb.append("C.ID AS ID_CLIENTE, C.NOME, C.CPF, C.TEL, C.ENDERECO, C.NUMERO, C.CIDADE, C.ESTADO, C.IDADE ");
        sb.append("FROM TB_VENDA V ");
        sb.append("INNER JOIN TB_CLIENTE C ON V.ID_CLIENTE_FK = C.ID ");
        return sb;
    }

    private String getQueryInsercaoProdQuant() {
        return "INSERT INTO TB_PRODUTO_QUANTIDADE (ID, ID_PRODUTO_FK, ID_VENDA_FK, QUANTIDADE, VALOR_TOTAL) VALUES (nextval('sq_produto_quantidade'),?,?,?,?)";
    }

    private void setParametrosQueryInsercaoProdQuant(PreparedStatement stm, Venda venda, ProdutoQuantidade prod) throws SQLException {
        stm.setLong(1, prod.getProduto().getId());
        stm.setLong(2, venda.getId());
        stm.setInt(3, prod.getQuantidade());
        stm.setBigDecimal(4, prod.getValorTotal());
    }

    @Override
    public Venda consultar(String valor) throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        StringBuilder sb = sqlBaseSelect();
        sb.append("WHERE V.CODIGO = ? ");
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(sb.toString());
            setParametrosQuerySelect(stm, valor);
            rs = stm.executeQuery();
            if (rs.next()) {
                Venda venda = VendaFactory.convert(rs);
                buscarAssociacaoVendaProdutos(connection, venda);
                return venda;
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Erro ao Consultar objeto ", e);
        } finally {
            closeConnection(connection, stm, rs);
        }
        return null;
    }

    private void buscarAssociacaoVendaProdutos(Connection connection, Venda venda) throws ExceptionDao {
        PreparedStatement stmProd = null;
        ResultSet rsProd = null;
        try {
            StringBuilder sbProd = new StringBuilder();
            sbProd.append("SELECT PQ.ID, PQ.QUANTIDADE, PQ.VALOR_TOTAL, ");
            sbProd.append("P.ID AS ID_PRODUTO, P.CODIGO, P.NOME, P.DESCRICAO, P.VALOR, P.QUANTIDADE_ESTOQUE, P.DATA_CRIACAO, P.DATA_ATUALIZACAO ");
            sbProd.append("FROM TB_PRODUTO_QUANTIDADE PQ ");
            sbProd.append("INNER JOIN TB_PRODUTO P ON P.ID = PQ.ID_PRODUTO_FK ");
            sbProd.append("WHERE PQ.ID_VENDA_FK = ?");
            stmProd = connection.prepareStatement(sbProd.toString());
            stmProd.setLong(1, venda.getId());
            rsProd = stmProd.executeQuery();
            Set<ProdutoQuantidade> produtos = new HashSet<>();
            while (rsProd.next()) {
                ProdutoQuantidade prodQ = ProdutoQuantidadeFactory.convert(rsProd);
                produtos.add(prodQ);
            }
            venda.setProdutos(produtos);
            venda.recalcularValorTotalVenda();
        } catch (SQLException e) {
            throw new ExceptionDao("ERRO CONSULTANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stmProd, rsProd);
        }
    }

    @Override
    public Collection<Venda> buscarTodos() throws ExceptionDao {
        List<Venda> lista = new ArrayList<>();
        StringBuilder sb = sqlBaseSelect();
        try {
            Connection connection = getConnection();
            PreparedStatement stm = connection.prepareStatement(sb.toString());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Venda venda = VendaFactory.convert(rs);
                buscarAssociacaoVendaProdutos(connection, venda);
                lista.add(venda);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("ERRO CONSULTANDO OBJETO ", e);
        }
        return lista;
    }

    @Override
    public Boolean cadastrar(Venda entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = getConnection();
            stm = connection.prepareStatement(getQueryInsercao(), Statement.RETURN_GENERATED_KEYS);
            setParametrosQueryInsercao(stm, entity);
            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setId(rs.getLong(1));
                    }
                }

                for (ProdutoQuantidade prod : entity.getProdutos()) {
                    stm = connection.prepareStatement(getQueryInsercaoProdQuant());
                    setParametrosQueryInsercaoProdQuant(stm, entity, prod);
                    rowsAffected = stm.executeUpdate();
                }

                return true;
            }
        } catch (SQLException e) {
            throw new ExceptionDao("ERRO CADASTRANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
        return false;
    }

    @Override
    public void cancelarVenda(Venda venda) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE TB_VENDA SET STATUS_VENDA = ? WHERE ID = ?";
            connection = getConnection();
            stm = connection.prepareStatement(sql);
            stm.setString(1, Status.CANCELADA.name());
            stm.setLong(2, venda.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("ERRO ATUALIZANDO OBJETO ", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }
}
