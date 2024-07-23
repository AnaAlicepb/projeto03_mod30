package dao;

import java.util.Collection;

import domain.Cliente;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public class ClienteDaoMock implements IClienteDAO {
	 
	
	public String salvar() {
	        return null;
	    }

	@Override
	public Boolean cadastrar(Cliente entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(Long valor) throws ExceptionDao {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Cliente entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente consultar(Long valor) throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
		Cliente cliente = new Cliente();
		cliente.setCpf(valor);
		return cliente;
	}

	@Override
	public Collection<Cliente> buscarTodos() throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}
}
