package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import dao.generic.GenericDAO;
import domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO {

    public ProdutoDAO() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setDescricao(entity.getDescricao());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setValor(entity.getValor());
        entityCadastrado.setQuantidadeEstoque(entity.getQuantidadeEstoque());
        entityCadastrado.setDataAtualizacao(LocalDateTime.now());
    }

    @Override
    protected String getQueryInsercao() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUTO ");
        sb.append("(ID, CODIGO, NOME, DESCRICAO, VALOR, QUANTIDADE_ESTOQUE, DATA_CRIACAO, DATA_ATUALIZACAO)");
        sb.append("VALUES (nextval('sq_produto'),?,?,?,?,?,?,?)");
        return sb.toString();
    }

    @Override
    protected String getQueryExclusao() {
        return "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
    }

    @Override
    protected String getQuerytAtualizacao() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_PRODUTO ");
        sb.append("SET CODIGO = ?,");
        sb.append("NOME = ?,");
        sb.append("DESCRICAO = ?,");
        sb.append("VALOR = ?,");
        sb.append("QUANTIDADE_ESTOQUE = ?,");
        sb.append("DATA_ATUALIZACAO = ?");
        sb.append(" WHERE CODIGO = ?");
        return sb.toString();
    }

    @Override
    protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Produto entity) throws SQLException {
        stmInsert.setString(1, entity.getCodigo());
        stmInsert.setString(2, entity.getNome());
        stmInsert.setString(3, entity.getDescricao());
        stmInsert.setBigDecimal(4, entity.getValor());
        stmInsert.setInt(5, entity.getQuantidadeEstoque());
        stmInsert.setTimestamp(6, Timestamp.valueOf(entity.getDataCriacao()));
        stmInsert.setTimestamp(7, Timestamp.valueOf(entity.getDataAtualizacao()));
    }

    @Override
    protected void setParametrosQueryExclusao(PreparedStatement stmExclusao, String valor) throws SQLException {
        stmExclusao.setString(1, valor);
    }

    @Override
    protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Produto entity) throws SQLException {
        stmUpdate.setString(1, entity.getCodigo());
        stmUpdate.setString(2, entity.getNome());
        stmUpdate.setString(3, entity.getDescricao());
        stmUpdate.setBigDecimal(4, entity.getValor());
        stmUpdate.setInt(5, entity.getQuantidadeEstoque());
        stmUpdate.setTimestamp(6, Timestamp.valueOf(entity.getDataAtualizacao()));
        stmUpdate.setString(7, entity.getCodigo());
    }

    @Override
    protected void setParametrosQuerySelect(PreparedStatement stmSelect, String valor) throws SQLException {
        stmSelect.setString(1, valor);
    }
}
