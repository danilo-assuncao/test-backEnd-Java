package br.com.system.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.system.dao.ext.Parameter;
import br.com.system.domain.EntityDomain;

public abstract class GenericDAOImpl<ID, E extends EntityDomain> implements GenericDAO<ID, E> {

	private Class<E> clazz;
	private EntityManager em;
	
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		this.clazz = getClasse();
	}

	@Override
	public void save(E entity) {
		em.persist(entity);
	}

	@Override
	public void remove(E entity) {
		em.remove(em.getReference(clazz, entity.getId()));
	}

	@Override
	public E update(E entity) {
		return em.merge(entity);
	}

	@Override
	public E find(ID id) {
		return em.find(clazz, id);
	}

	@Override
	public List<E> find(String namedQuery, Parameter... parameters) {
		TypedQuery<E> query = em.createNamedQuery(namedQuery, clazz);
		for(Parameter parameter : parameters) {
			query.setParameter(parameter.getName(), parameter.getValue());
		}
		return query.getResultList();
	}
	
	@Override
	public List<E> findAll() {
		TypedQuery<E> query = em.createQuery("select e from " + clazz.getSimpleName() + " e", clazz);
		return query.getResultList();
	}
	
	/**
	 * Obtem a classe abstraida.
	 * 
	 * @return classe abstraida
	 */
	@SuppressWarnings("unchecked")
	private Class<E> getClasse() {
		Class<E> clazz = (Class<E>) ((ParameterizedType) 
				getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
}