package test;

import exceptions.ExceptionDao;
import exceptions.ExceptionTipoChaveNaoEncontrada;
import services.ClienteService;
import services.IClienteService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;

public class ClienteServiceTest {
    
    private IClienteService clienteService;
    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDAO(); // Use ClienteDAO real
        clienteService = new ClienteService(dao);
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(12345678901L);
        cliente.setNome("João da Silva");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("Rua A");
        cliente.setEstado("SP");
        cliente.setNumero(100);
        cliente.setTelefone(11999999999L);
        cliente.setIdade(30L);
        cliente.setEmail("joao.silva@example.com");
    }

    @Test
    public void pesquisarCliente() throws ExceptionDao, ExceptionTipoChaveNaoEncontrada {
        clienteService.cadastrar(cliente); // Certifique-se de que o cliente foi cadastrado antes de pesquisar
        Cliente clienteConsultado = clienteService.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        Assert.assertEquals(cliente.getCpf(), clienteConsultado.getCpf());
    }

    @Test
    public void salvarCliente() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Boolean retorno = clienteService.cadastrar(cliente);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente() throws ExceptionDao, ExceptionTipoChaveNaoEncontrada {
        clienteService.cadastrar(cliente); // Certifique-se de que o cliente foi cadastrado antes de excluir
        clienteService.excluir(cliente.getCpf());
        Cliente clienteConsultado = clienteService.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        clienteService.cadastrar(cliente); // Certifique-se de que o cliente foi cadastrado antes de alterar
        cliente.setNome("João da Silva Jr.");
        clienteService.alterar(cliente);
        Cliente clienteAlterado = clienteService.consultar(cliente.getCpf());
        Assert.assertEquals("João da Silva Jr.", clienteAlterado.getNome());
    }
}
