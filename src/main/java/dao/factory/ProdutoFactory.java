package dao.factory;

import java.sql.ResultSet;
import java.sql.SQLException;


import domain.Produto;

public class ProdutoFactory {

    public static Produto convert(ResultSet rs) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getLong("ID"));
        prod.setCodigo(rs.getString("CODIGO"));
        prod.setNome(rs.getString("NOME"));
        prod.setDescricao(rs.getString("DESCRICAO"));
        prod.setValor(rs.getBigDecimal("VALOR"));
        prod.setQuantidadeEstoque(rs.getInt("QUANTIDADE_ESTOQUE"));
        prod.setDataCriacao(rs.getTimestamp("DATA_CRIACAO").toLocalDateTime());
        prod.setDataAtualizacao(rs.getTimestamp("DATA_ATUALIZACAO").toLocalDateTime());
        return prod;
    }
}
