package domain;

import java.math.BigDecimal;

import annotation.ColunaTabela;
import annotation.Tabela;

@Tabela("tb_produto_quantidade")
public class ProdutoQuantidade {

    @ColunaTabela(dbName = "id", setJavaName = "setIdQuantidadeProduto")
    private Long idQuantidadeProduto;

    @ColunaTabela(dbName = "id_produto_fk", setJavaName = "setIdProdutoFk")
    private Long idProdutoFk;

    @ColunaTabela(dbName = "id_venda_fk", setJavaName = "setIdVendaFk")
    private Long idVendaFk;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
    private Integer quantidade;

    @ColunaTabela(dbName = "valor_total", setJavaName = "setValorTotal")
    private BigDecimal valorTotal;

    private Produto produto;

    public Long getIdQuantidadeProduto() {
        return idQuantidadeProduto;
    }

    public void setIdQuantidadeProduto(Long idQuantidadeProduto) {
        this.idQuantidadeProduto = idQuantidadeProduto;
    }

    public Long getIdProdutoFk() {
        return idProdutoFk;
    }

    public void setIdProdutoFk(Long idProdutoFk) {
        this.idProdutoFk = idProdutoFk;
    }

    public Long getIdVendaFk() {
        return idVendaFk;
    }

    public void setIdVendaFk(Long idVendaFk) {
        this.idVendaFk = idVendaFk;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoQuantidade() {
        this.quantidade = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    public void adicionar(Integer quantidade) {
        this.quantidade += quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(Integer quantidade) {
        this.quantidade -= quantidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quantidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }
}
