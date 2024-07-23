package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public class ProdutoDAOTest {
    
    private IProdutoDAO produtoDao;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @After
    public void end() throws ExceptionDao {
        Collection<Produto> list = produtoDao.buscarTodos();
        list.forEach(prod -> {
            try {
                produtoDao.excluir(prod.getCodigo());
            } catch (ExceptionDao e) {
                e.printStackTrace();
            }
        });
    }

    private Produto criarProduto(String codigo) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setNome("Produto Exemplo");
        produto.setDescricao("Descrição do Produto Exemplo");
        produto.setValor(BigDecimal.TEN);
        produto.setQuantidadeEstoque(100);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataAtualizacao(LocalDateTime.now());
        produtoDao.cadastrar(produto);
        return produto;
    }

    @Test
    public void pesquisar() throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao, ExceptionTipoChaveNaoEncontrada {
        Produto produto = criarProduto("P1");
        Assert.assertNotNull(produto);
        Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
        Assert.assertNotNull(produtoDB);
    }

    @Test
    public void salvar() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Produto produto = criarProduto("P2");
        Assert.assertNotNull(produto);
    }

    @Test
    public void excluir() throws ExceptionDao, ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable {
        Produto produto = criarProduto("P3");
        Assert.assertNotNull(produto);
        produtoDao.excluir(produto.getCodigo());
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNull(produtoBD);
    }

    @Test
    public void alterarProduto() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao, ExceptionMaisDeUmRegistro, ExceptionTable {
        Produto produto = criarProduto("P4");
        produto.setNome("Produto Alterado");
        produtoDao.alterar(produto);
        Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        Assert.assertEquals("Produto Alterado", produtoBD.getNome());
    }

    @Test
    public void buscarTodos() throws ExceptionDao, ExceptionTipoChaveNaoEncontrada {
        criarProduto("P5");
        criarProduto("P6");
        Collection<Produto> list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        for (Produto prod : list) {
            produtoDao.excluir(prod.getCodigo());
        }

        list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }
}
