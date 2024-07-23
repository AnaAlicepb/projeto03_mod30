package test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

import domain.Cliente;

public class ClienteDAOTest {
    
    private IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }
    
    @After
    public void end() throws ExceptionDao {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (ExceptionDao e) {
                e.printStackTrace();
            }
        });
    }
    
    @Test
    public void pesquisarCliente() throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Cliente cliente = new Cliente();
        cliente.setCpf(11111111111L);
        cliente.setNome("Carlos Alberto");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("Rua 1");
        cliente.setEstado("SP");
        cliente.setNumero(100);
        cliente.setTelefone(1199999999L);
        cliente.setIdade(30L);
        cliente.setEmail("carlos.alberto@example.com");
        clienteDao.cadastrar(cliente);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteDao.excluir(cliente.getCpf());
    }
    
    @Test
    public void salvarCliente() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = new Cliente();
        cliente.setCpf(22222222222L);
        cliente.setNome("Patrícia Lima");
        cliente.setCidade("Rio de Janeiro");
        cliente.setEndereco("Rua 2");
        cliente.setEstado("RJ");
        cliente.setNumero(200);
        cliente.setTelefone(2199999999L);
        cliente.setIdade(28L);
        cliente.setEmail("patricia.lima@example.com");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteDao.excluir(cliente.getCpf());
    }
    
    @Test
    public void excluirCliente() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = new Cliente();
        cliente.setCpf(33333333333L);
        cliente.setNome("Joana Santos");
        cliente.setCidade("Belo Horizonte");
        cliente.setEndereco("Rua 3");
        cliente.setEstado("MG");
        cliente.setNumero(300);
        cliente.setTelefone(3199999999L);
        cliente.setIdade(35L);
        cliente.setEmail("joana.santos@example.com");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }
    
    @Test
    public void alterarCliente() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = new Cliente();
        cliente.setCpf(44444444444L);
        cliente.setNome("Fernando Costa");
        cliente.setCidade("Curitiba");
        cliente.setEndereco("Rua 4");
        cliente.setEstado("PR");
        cliente.setNumero(400);
        cliente.setTelefone(4199999999L);
        cliente.setIdade(40L);
        cliente.setEmail("fernando.costa@example.com");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteConsultado.setNome("Fernando da Costa");
        clienteDao.alterar(clienteConsultado);
        
        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Fernando da Costa", clienteAlterado.getNome());
        
        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }
    
    @Test
    public void buscarTodos() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Cliente cliente1 = new Cliente();
        cliente1.setCpf(55555555555L);
        cliente1.setNome("Gabriela Souza");
        cliente1.setCidade("Salvador");
        cliente1.setEndereco("Rua 5");
        cliente1.setEstado("BA");
        cliente1.setNumero(500);
        cliente1.setTelefone(7199999999L);
        cliente1.setIdade(32L);
        cliente1.setEmail("gabriela.souza@example.com");
        Boolean retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertTrue(retorno1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setCpf(66666666666L);
        cliente2.setNome("Renato Fernandes");
        cliente2.setCidade("Porto Alegre");
        cliente2.setEndereco("Rua 6");
        cliente2.setEstado("RS");
        cliente2.setNumero(600);
        cliente2.setTelefone(5199999999L);
        cliente2.setIdade(45L);
        cliente2.setEmail("renato.fernandes@example.com");
        Boolean retorno2 = clienteDao.cadastrar(cliente2);
        Assert.assertTrue(retorno2);
        
        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);
        
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (ExceptionDao e) {
                e.printStackTrace();
            }
        });
        
        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }
}
