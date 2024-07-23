package services.generic;

import java.io.Serializable;
import java.util.Collection;

import dao.Persistente;
import dao.generic.IGenericDAO;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public abstract class GenericService<T extends Persistente, E extends Serializable> implements IGenericService<T, E>  {

	protected IGenericDAO<T, E> dao;
	
	
	public GenericService(IGenericDAO<T, E> dao) {
		this.dao = dao;
	}
	
	public Boolean cadastrar(T entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao{
		return this.dao.cadastrar(entity);
	}
	
	public void excluir(E valor) throws ExceptionDao{
		this.dao.excluir(valor);
	}
	
	public void alterar(T entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao {
		this.dao.alterar(entity);
	}
	public T consultar(E valor) throws ExceptionDao {
		try {
			return this.dao.consultar(valor);
		} catch (ExceptionMaisDeUmRegistro | ExceptionTable e) {
			// TODO Auto-generated catch block
			//TODO levantar um erro genérico
			e.printStackTrace();
		}
		return null;
	}
	public Collection<T> buscarTodos() throws ExceptionDao {
		return this.dao.buscarTodos();
	}
	
}
