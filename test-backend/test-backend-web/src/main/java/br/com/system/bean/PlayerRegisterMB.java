package br.com.system.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.system.domain.Player;
import br.com.system.service.PlayerService;
import br.com.system.util.FacesUtils;
import br.com.system.util.HTTPUtils;
import br.com.system.util.ObjectMapperUtils;

/**
 * ManagedBean da tela de cadastro de jogadores.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@Named
@ViewScoped
public class PlayerRegisterMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlayerService playerService;
	
	// Dados do jogador
	private Player player;
	
	@PostConstruct
	private void init() {
		player = new Player();
		player.setGroup(Player.GROUP_LIGA_JUSTICA);
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Obtem todos os jogadores cadastrados na base de dados.
	 * 
	 * @return lista com os jogadores ou lista vázia
	 */
	private List<Player> getAllPlayers() {
		List<Player> players = playerService.findAll();
		if (players == null) {
			return Collections.emptyList();
		}
		return players;
	}
	
	/**
	 * Obtém um codinome para cadastro.
	 * 
	 * @return 
	 * - sucesso: Codinome
	 * - erro: null indicando que nao existe codinome sobrando ou erros gerais
	 */
	private String getAvailableCodename() {
		String file = null;
		List<String> codenames = null;
		
		// Obtem o arquivo correspondente
		if (player.getGroup().equals(Player.GROUP_VINGADORES)) {
			file = HTTPUtils.sendGETRequest(HTTPUtils.URL_VINGADORES);
		} else if (player.getGroup().equals(Player.GROUP_LIGA_JUSTICA)) {
			file = HTTPUtils.sendGETRequest(HTTPUtils.URL_LIGA_JUSTICA);
		}
		if (file == null) {
			return null;
		}
		
		// Obtem a lista de codinomes correspondente
		if (player.getGroup().equals(Player.GROUP_VINGADORES)) {
			codenames = ObjectMapperUtils.getVingadoresCodenames(file);
		} else if (player.getGroup().equals(Player.GROUP_LIGA_JUSTICA)) {
			codenames = ObjectMapperUtils.getLigaJusticaCodenames(file);
		}
		if (codenames == null) {
			return null;
		}
		
		// Obtem a lista com todos os jogadores
		List<Player> players = getAllPlayers();
		for (Player p : players) {
			codenames.remove(p.getCodename());
		}
		if (codenames.isEmpty()) {
			return null;
		}
		return codenames.get(0);
	}
	
	/**
	 * Valida os atributos do objeto Player.
	 * 
	 * @param player dados do jogador a serem validados
	 */
	private boolean validFields(Player player) {
		if (player == null) {
			return false;
		} else if (player.getName().isEmpty()) {
			FacesUtils.errorMessage("O nome do jogador deve ser preenchido");
			return false;
		} else if (player.getEmail().isEmpty()) {
			FacesUtils.errorMessage("O e-mail do jogador deve ser preenchido");
			return false;
		} else if (player.getTelephone().isEmpty()) {
			FacesUtils.errorMessage("O telefone do jogador deve ser preenchido");
			return false;			
		}
		return true;
	}
	
	/**
	 * Salva os dados do jogador.
	 */
	public void savePlayer() {
		if (player != null) {
			String codename = getAvailableCodename();
			if (codename != null) {
				player.setCodename(codename);
				if (validFields(player)) {
					playerService.save(player);
					FacesUtils.successMessage(player.getName() + " entrou para o time " + player.getGroup());
					init();
				}
			} else {
				FacesUtils.errorMessage("O time " + player.getGroup() + " não possui mais codinomes disponíveis");
			}
		}
	}
	
	/**
	 * Lista os jogadores já cadastrados no sistema.
	 * 
	 * @return redireciona para a página 'player_list'
	 */
	public String listPlayers() {
		return "PLAYER_LIST";
	}
}