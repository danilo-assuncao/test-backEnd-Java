package br.com.system.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.system.dto.CodenameDTO;
import br.com.system.dto.LigaJusticaDTO;
import br.com.system.dto.VingadoresDTO;

/**
 * Classe util para conversões de arquivos JSON e XML para 
 * Objetos do tipo EntityDomain.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class ObjectMapperUtils {

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperUtils.class);
	
	/**
	 * Obtém a lista com todos os codinomes contidos no arquivo dos Vingadores.
	 * 
	 * @param json arquivo json
	 * @return 
	 * - sucesso: Lista com todos os codinomes dos vingadores<br>
	 * - erro: null
	 */
	public static List<String> getVingadoresCodenames(String json) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			VingadoresDTO vingadores = objectMapper.readValue(json, VingadoresDTO.class);
			List<String> list = new ArrayList<String>();
		    for (CodenameDTO dto : vingadores.getCodenames()) {
		    	list.add(dto.getCodename());
		    }
		    if (! list.isEmpty()) {
		    	Collections.sort(list);
		    }
		    return list;
		} catch (IOException e) {
			LOGGER.error(e.toString(), e);
		}
		return null;
	}
	
	/**
	 * Obtém a lista com todos os codinomes contidos no arquivo da Liga da Justiça.
	 * 
	 * @param xml arquivo xml
	 * @return 
	 * - sucesso: Lista com todos os codinomes da liga da justiça<br>
	 * - erro: null
	 */
	public static List<String> getLigaJusticaCodenames(String xml) {
		try {
			XmlMapper xmlMapper = new XmlMapper();
			LigaJusticaDTO liga = xmlMapper.readValue(xml, LigaJusticaDTO.class);
			List<String> list = new ArrayList<String>();
			for (String codename : liga.getCodenames()) {
				list.add(codename);
			}
		    if (! list.isEmpty()) {
		    	Collections.sort(list);
		    }
			return list;
		} catch (IOException e) {
			LOGGER.error(e.toString(), e);
		}
		return null;
	}
}