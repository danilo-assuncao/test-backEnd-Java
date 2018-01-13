package br.com.system.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.system.dao.ext.Parameter;
import br.com.system.domain.Player;

/**
 * DAO da classe Player.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class PlayerDAO extends GenericDAOImpl<Integer, Player> {

	public PlayerDAO(EntityManager em) {
		super(em);
	}
	
	/**
	 * Busca jogadores pelo nome.
	 * 
	 * @param name nome para identificação dos jogadores.
	 * @return lista com jogadores ou lista vázia
	 */
	public List<Player> findByName(String name) {
		Parameter pName = new Parameter("name", "%" + name + "%");
		return super.find(Player.NQ_FIND_BY_NAME, pName);
	}
	
	/**
	 * Busca jogadores pelo e-mail.
	 * 
	 * @param email email para identificação dos jogadores.
	 * @return lista com jogadores ou lista vázia
	 */
	public List<Player> findByEmail(String email) {
		Parameter pEmail = new Parameter("email", "%" + email + "%");
		return super.find(Player.NQ_FIND_BY_EMAIL, pEmail);
	}
	
	/**
	 * Busca jogadores pelo telefone.
	 * 
	 * @param telephone telefone para identificação dos jogadores.
	 * @return lista com jogadores ou lista vázia
	 */
	public List<Player> findByTelephone(String telephone) {
		Parameter pTelephone = new Parameter("telephone", "%" + telephone + "%");
		return super.find(Player.NQ_FIND_BY_TELEPHONE, pTelephone);
	}
	
	/**
	 * Busca jogadores pelo codinome.
	 * 
	 * @param codename codinome para identificação dos jogadores.
	 * @return lista com jogadores ou lista vázia
	 */
	public List<Player> findByCodename(String codename) {
		Parameter pCodename = new Parameter("codename", "%" + codename + "%");
		return super.find(Player.NQ_FIND_BY_CODENAME, pCodename);
	}
	
	/**
	 * Busca jogadores pelo grupo.
	 * 
	 * @param name nome para identificação dos jogadores.
	 * @return lista com jogadores ou lista vázia
	 */
	public List<Player> findByGroup(String group) {
		Parameter pGroup = new Parameter("group", "%" + group + "%");
		return super.find(Player.NQ_FIND_BY_GROUP, pGroup);
	}
}