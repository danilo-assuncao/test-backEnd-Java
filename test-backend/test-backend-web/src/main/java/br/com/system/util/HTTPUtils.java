package br.com.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe util com recursos para HTTP.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class HTTPUtils {

	private static final String BASE_URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/";
	public static final String URL_VINGADORES = BASE_URL + "vingadores.json";
	public static final String URL_LIGA_JUSTICA = BASE_URL + "liga_da_justica.xml";
	
	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPUtils.class);
	
	/**
	 * Envia uma requisição HTTP usando método GET.
	 * 
	 * @param url url para requisição
	 * @return 
	 * - sucesso: dados retornados da requisição<br>
	 * - erro: null em caso de erro
	 */
	public static String sendGETRequest(String url) {
		String response = null;
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			if (conn == null) {
				LOGGER.warn("Erro ao obter o objeto de conexão");
				return response;
			}
			int rc = conn.getResponseCode();
			if (rc != HttpURLConnection.HTTP_OK) {
				LOGGER.warn("Erro ao enviar a requisição. Código de resposta: " + rc);
				return response;
			}
			
			// Leitura do arquivo UTF-8
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			response = sb.toString();
		} catch (IOException e) {
			LOGGER.warn(e.toString(), e);
		}
		return response;
	}
}