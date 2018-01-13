package br.com.system.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe tipo DTO para obter os codinomes dos Vingadores.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class VingadoresDTO {

	@JsonProperty("vingadores")
	private List<CodenameDTO> codenames;

	/**
	 * @return the codenames
	 */
	public List<CodenameDTO> getCodenames() {
		return codenames;
	}

	/**
	 * @param codenames the codenames to set
	 */
	public void setCodenames(List<CodenameDTO> codenames) {
		this.codenames = codenames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codenames == null) ? 0 : codenames.hashCode());
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
		VingadoresDTO other = (VingadoresDTO) obj;
		if (codenames == null) {
			if (other.codenames != null)
				return false;
		} else if (!codenames.equals(other.codenames))
			return false;
		return true;
	}
}