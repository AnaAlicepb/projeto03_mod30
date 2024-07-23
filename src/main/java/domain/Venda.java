package domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import annotation.ColunaTabela;
import annotation.Tabela;
import annotation.TipoChave;
import dao.Persistente;

@Tabela("tb_venda")
public class Venda implements Persistente {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long idVenda;

    @TipoChave("getCodigo")
    @ColunaTabela(dbName = "codigo", setJavaName = "setCodigo")
    private String codigo;

    @ColunaTabela(dbName = "id_cliente_fk", setJavaName = "setIdClienteFk")
    private Long idClienteFk; // ID do cliente como chave estrangeira

    private Cliente cliente;

    private Set<ProdutoQuantidade> produtos;

    @ColunaTabela(dbName = "valor_total", setJavaName = "setValorTotal")
    private BigDecimal valorTotal;

    @ColunaTabela(dbName = "data_venda", setJavaName = "setDataVenda")
    private Instant dataVenda;

    @ColunaTabela(dbName = "status_venda", setJavaName = "setStatus")
    private Status status;

    public Venda() {
        produtos = new HashSet<>();
    }

    public void adicionarProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> op = produtos.stream()
                .filter(f -> f.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if (op.isPresent()) {
            ProdutoQuantidade produtoQtd = op.get();
            produtoQtd.adicionar(quantidade);
        } else {
            ProdutoQuantidade prod = new ProdutoQuantidade();
            prod.setProduto(produto);
            prod.adicionar(quantidade);
            produtos.add(prod);
        }
        recalcularValorTotalVenda();
    }

    public void recalcularValorTotalVenda() {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoQuantidade prod : this.produtos) {
            valorTotal = valorTotal.add(prod.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    private void validarStatus() {
        if (this.status == Status.CONCLUIDA) {
            throw new UnsupportedOperationException("Impossível alterar venda finalizada.");
        }
    }

    public void removerProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> op = produtos.stream()
                .filter(f -> f.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if (op.isPresent()) {
            ProdutoQuantidade produtoQtd = op.get();
            if (produtoQtd.getQuantidade() > quantidade) {
                produtoQtd.remover(quantidade);
            } else {
                produtos.remove(op.get());
            }
        }
        recalcularValorTotalVenda();
    }

    public void removerTodosProdutos() {
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQuantidadeTotalProdutos() {
        return produtos.stream().reduce(0, (acumulador, prod) -> acumulador + prod.getQuantidade(), Integer::sum);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.idClienteFk = cliente.getId();
    }

    public Set<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoQuantidade> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Long getId() {
        return idVenda;
    }

    @Override
    public void setId(Long id) {
        this.idVenda = id;
    }
}
