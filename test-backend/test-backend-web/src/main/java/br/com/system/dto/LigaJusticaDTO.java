package br.com.system.dto;

import java.util.Arrays;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Classe tipo DTO para obter os codinomes da Liga da Justiça.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@JacksonXmlRootElement(localName = "liga_da_justica") 
public class LigaJusticaDTO {

	@JacksonXmlProperty(localName = "codinomes")
	private String[] codenames;

	/**
	 * @return the codenames
	 */
	public String[] getCodenames() {
		return codenames;
	}

	/**
	 * @param codenames the codenames to set
	 */
	public void setCodenames(String[] codenames) {
		this.codenames = codenames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(codenames);
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
		LigaJusticaDTO other = (LigaJusticaDTO) obj;
		if (!Arrays.equals(codenames, other.codenames))
			return false;
		return true;
	}
}