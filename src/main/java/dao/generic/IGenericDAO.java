package dao.generic;

import java.io.Serializable;
import java.util.Collection;

import dao.Persistente;
import exceptions.ExceptionDao;
import exceptions.ExceptionMaisDeUmRegistro;
import exceptions.ExceptionTable;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public interface IGenericDAO<T extends Persistente, E extends Serializable> {

    public Boolean cadastrar(T entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao;

    public void excluir(E valor) throws ExceptionDao;

    public void alterar(T entity) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao;

    public T consultar(E valor) throws ExceptionMaisDeUmRegistro, ExceptionTable, ExceptionDao;

    public Collection<T> buscarTodos() throws ExceptionDao;
}
