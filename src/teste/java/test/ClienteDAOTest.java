package test;

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
    public void tearDown() throws ExceptionDao {
        Collection<Cliente> list = clienteDao.buscarTodos();
        for (Cliente cliente : list) {
            clienteDao.excluir(cliente.getCpf());
        }
    }

    private Cliente criarCliente(Long cpf, String nome) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setCidade("Cidade Exemplo");
        cliente.setEndereco("Endereco Exemplo");
        cliente.setEstado("EX");
        cliente.setNumero(123);
        cliente.setTelefone(1199999999L);
        cliente.setIdade(30L);
        cliente.setEmail(nome.toLowerCase().replace(" ", ".") + "@example.com");
        return cliente;
    }

    @Test
    public void testPesquisarCliente_ClienteExistente_RetornaCliente() throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Cliente cliente = criarCliente(11111111111L, "Carlos Alberto");
        clienteDao.cadastrar(cliente);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        Assert.assertEquals("Carlos Alberto", clienteConsultado.getNome());
    }
    
    @Test
    public void testSalvarCliente_ClienteNovo_CadastradoComSucesso() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = criarCliente(22222222222L, "Patrícia Lima");
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        Assert.assertEquals("Patrícia Lima", clienteConsultado.getNome());
    }
    
    @Test
    public void testExcluirCliente_ClienteExistente_ExcluidoComSucesso() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = criarCliente(33333333333L, "Joana Santos");
        clienteDao.cadastrar(cliente);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }
    
    @Test
    public void testAlterarCliente_ClienteExistente_AlteradoComSucesso() throws ExceptionTipoChaveNaoEncontrada, ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
        Cliente cliente = criarCliente(44444444444L, "Fernando Costa");
        clienteDao.cadastrar(cliente);
        
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        
        clienteConsultado.setNome("Fernando da Costa");
        clienteDao.alterar(clienteConsultado);
        
        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("Fernando da Costa", clienteAlterado.getNome());
    }
    
    @Test
    public void testBuscarTodos_DoisClientesExistentes_RetornaDoisClientes() throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
        Cliente cliente1 = criarCliente(55555555555L, "Gabriela Souza");
        Cliente cliente2 = criarCliente(66666666666L, "Renato Fernandes");
        
        clienteDao.cadastrar(cliente1);
        clienteDao.cadastrar(cliente2);
        
        Collection<Cliente> list = clienteDao.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }
}
