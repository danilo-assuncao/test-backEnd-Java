package br.com.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe que representa o Jogador.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@Entity
@Table(name = "player")
@NamedQueries({
	@NamedQuery(name = Player.NQ_FIND_BY_NAME, query = "select p from Player p where p.name like :name"),
	@NamedQuery(name = Player.NQ_FIND_BY_EMAIL, query = "select p from Player p where p.email like :email"),
	@NamedQuery(name = Player.NQ_FIND_BY_TELEPHONE, query = "select p from Player p where p.telephone like :telephone"),
	@NamedQuery(name = Player.NQ_FIND_BY_CODENAME, query = "select p from Player p where p.codename like :codename"),
	@NamedQuery(name = Player.NQ_FIND_BY_GROUP, query = "select p from Player p where p.group like :group")
})
public class Player extends EntityDomain {

	// Grupos Disponíveis para Jogadores
	public static final String GROUP_LIGA_JUSTICA = "Liga da Justiça";
	public static final String GROUP_VINGADORES = "Vingadores";
	
	// Namedqueries
	public static final String NQ_FIND_BY_NAME = "Player.findByName";
	public static final String NQ_FIND_BY_EMAIL = "Player.findByEmail";
	public static final String NQ_FIND_BY_TELEPHONE = "Player.findByTelephone";
	public static final String NQ_FIND_BY_CODENAME = "Player.findByCodename";
	public static final String NQ_FIND_BY_GROUP = "Player.findByGroup";
	
	public Player() {
		super();
	}

	public Player(String name, String email, String telephone, String codename, String group) {
		super();
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.codename = codename;
		this.group = group;
	}

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "email", length = 150, nullable = false)
	private String email;

	@Column(name = "telephone", length = 15, nullable = false)
	private String telephone;

	@Column(name = "codename", length = 100, nullable = false)
	private String codename;

	@Column(name = "team", length = 25, nullable = false)
	private String group;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the codename
	 */
	public String getCodename() {
		return codename;
	}

	/**
	 * @param codename the codename to set
	 */
	public void setCodename(String codename) {
		this.codename = codename;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codename == null) ? 0 : codename.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (codename == null) {
			if (other.codename != null)
				return false;
		} else if (!codename.equals(other.codename))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}
}