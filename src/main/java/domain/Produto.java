package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import annotation.ColunaTabela;
import annotation.Tabela;
import annotation.TipoChave;
import dao.Persistente;

@Tabela("tb_produto")
public class Produto implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long idProduto;

    @TipoChave("getCodigo")
    @ColunaTabela(dbName = "codigo", setJavaName = "setCodigo")
    private String codigo;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;

    @ColunaTabela(dbName = "descricao", setJavaName = "setDescricao")
    private String descricao;

    @ColunaTabela(dbName = "valor", setJavaName = "setValor")
    private BigDecimal valor;

    @ColunaTabela(dbName = "quantidade_estoque", setJavaName = "setQuantidadeEstoque")
    private Integer quantidadeEstoque;

    @ColunaTabela(dbName = "data_criacao", setJavaName = "setDataCriacao")
    private LocalDateTime dataCriacao;

    @ColunaTabela(dbName = "data_atualizacao", setJavaName = "setDataAtualizacao")
    private LocalDateTime dataAtualizacao;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    // Herdado da interface Persistente
    public Long getId() {
        return idProduto;
    }

    // Herdado da interface Persistente
    public void setId(Long id) {
        this.idProduto = id;
    }
}
