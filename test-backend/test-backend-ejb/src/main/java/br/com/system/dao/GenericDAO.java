package br.com.system.dao;

import java.util.List;

import br.com.system.dao.ext.Parameter;

/**
 * Interface do DAO Generico.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 * 
 * @param <ID> Tipo ID
 * @param <E> Tipo Classe
 */
public interface GenericDAO<ID, E> {

	/**
	 * Salva uma entidade na base de dados.
	 * 
	 * @param entity dados da entidade
	 */
	public void save(E entity);
	
	/**
	 * Remove uma entidade da base de dados.
	 * 
	 * @param entity dados da entidade
	 */
	public void remove(E entity);

	/**
	 * Atualiza os dados de uma entidade na base de dados.
	 * 
	 * @param entity dados da entidade
	 * @return dados da entidade atualizada
	 */
	public E update(E entity);

	/**
	 * Busca uma entidade pelo seu ID.
	 * 
	 * @param id id da entidade
	 * @return entidade encontrada ou nulo
	 */
	public E find(ID id);

	/**
	 * Busca uma entidade através de uma namedquery.
	 * 
	 * @param namedQuery identificação da namedquery
	 * @param parameters lista de parametros
	 * @return lista com os objetos encontrados ou lista vázia
	 */
	public List<E> find(String namedQuery, Parameter... parameters);
	
	/**
	 * Trás todos os objetos armazenados em uma determinada tabela.
	 * 
	 * @return lista com as entidades ou lista vázia
	 */
	public List<E> findAll();
}