package br.com.system.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.system.dao.PlayerDAO;
import br.com.system.domain.Player;
import br.com.system.exception.PlayerException;
import br.com.system.util.JPAUtils;

/**
 * Serviço da classe Player.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@Stateless
public class PlayerService {

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);
	
	@PersistenceContext(name = JPAUtils.PERSISTENCE_UNIT)
	private EntityManager em;
	
	/**
	 * Salva um jogador na base de dados.
	 * 
	 * @param player dados do jogador
	 * @throws PlayerException erro ao salvar um jogador na base de dados.
	 */
	public void save(Player player) throws PlayerException {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			dao.save(player);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
			throw new PlayerException(e);
		}
	}
	
	/**
	 * Remove um jogador da base de dados.
	 * 
	 * @param player dados do jogador
	 * @throws PlayerException erro ao remover o jogador da base de dados
	 */
	public void remove(Player player) throws PlayerException {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			dao.remove(player);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
			throw new PlayerException(e);
		}
	}
	
	/**
	 * Atualiza os dados de um jogador na base de dados.
	 * 
	 * @param player dados do jogador
	 * @return dados do jogador atualizados
	 * @throws PlayerException erro ao atualizar os dados do jogador na base de dados
	 */
	public Player update(Player player) throws PlayerException {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.update(player);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
			throw new PlayerException(e);
		}
	}
	
	/**
	 * Busca jogadores pelo nome.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findByName(String name) {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findByName(name);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Busca jogadores pelo email.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findByEmail(String email) {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findByEmail(email);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Busca jogadores pelo telefone.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findByTelephone(String telephone) {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findByTelephone(telephone);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Busca jogadores pelo codinome.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findByCodename(String codename) {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findByCodename(codename);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Busca jogadores pelo grupo.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findByGroup(String group) {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findByGroup(group);
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
	
	/**
	 * Busca todos os jogadores cadastrados no banco de dados.
	 * 
	 * @return lista com jogadores ou lista vázia
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Player> findAll() {
		try {
			PlayerDAO dao = new PlayerDAO(em);
			return dao.findAll();
		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		}
		return Collections.emptyList();
	}
}