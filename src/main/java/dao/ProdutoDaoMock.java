package dao;

import java.util.Collection;

import domain.Produto;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public class ProdutoDaoMock implements IProdutoDAO {

	@Override
	public Boolean cadastrar(Produto entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(String valor) throws ExceptionDao {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Produto entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto consultar(String valor) throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao {
		Produto produto = new Produto();
		produto.setCodigo(valor);
		return produto;
	}

	@Override
	public Collection<Produto> buscarTodos() throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}

}
