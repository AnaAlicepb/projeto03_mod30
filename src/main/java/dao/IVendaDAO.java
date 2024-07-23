package dao;

import dao.generic.IGenericDAO;
import domain.Venda;
import exceptions.ExceptionDao;
import exceptions.ExceptionTipoChaveNaoEncontrada;

public interface IVendaDAO extends IGenericDAO<Venda, String> {
    
    void finalizarVenda(Venda venda) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao;
    
    void cancelarVenda(Venda venda) throws ExceptionTipoChaveNaoEncontrada, ExceptionDao;
}
