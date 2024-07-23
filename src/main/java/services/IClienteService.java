package services;


import domain.Cliente;
import exceptions.ExceptionDao;

import services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {
	
	Cliente buscarPorCPF(Long cpf) throws ExceptionDao;
	
//	void excluir(Long cpf);
//
//	void alterar(Cliente cliente) throws ExceptionTipoChaveNaoEncontrada;
	
//	Boolean cadastrar(Cliente cliente) throws ExceptionTipoChaveNaoEncontrada;


}
