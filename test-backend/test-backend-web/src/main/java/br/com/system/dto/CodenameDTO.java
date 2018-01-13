package br.com.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe DTO para captura dos codinomes dos Vingadores.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class CodenameDTO {

	@JsonProperty("codinome")
	private String codename;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codename == null) ? 0 : codename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodenameDTO other = (CodenameDTO) obj;
		if (codename == null) {
			if (other.codename != null)
				return false;
		} else if (!codename.equals(other.codename))
			return false;
		return true;
	}
}
