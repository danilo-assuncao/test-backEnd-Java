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

/**
 * ManagedBean da tela de listagem de todos os 
 * jogadores existentes.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@Named
@ViewScoped
public class PlayerListMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlayerService playerService;
	
	// Jogadores cadastrados
	private List<Player> players;
	
	// Valor a ser pesquisado
	private String valueSearch;
	
	// Valor obtido no componente OneMenu de pesquisa
	private int selectedSearch;

	// Nomenclatura utilizada no campo critério de pesquisa
	private String titleFieldCriterion;
	
	// Indicação se o campo critério de pesquisa deve ser exibido ou não
	private boolean renderFieldCriterion;
	
	// Para armazenar os dados do jogador que receberá alguma ação
	private Player selectedPlayer;
	
	@PostConstruct
	public void init() {
		players = getAllPlayers();
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	/**
	 * @return the valueSearch
	 */
	public String getValueSearch() {
		return valueSearch;
	}

	/**
	 * @param valueSearch the valueSearch to set
	 */
	public void setValueSearch(String valueSearch) {
		this.valueSearch = valueSearch;
	}

	/**
	 * @return the selectedSearch
	 */
	public int getSelectedSearch() {
		return selectedSearch;
	}

	/**
	 * @param selectedSearch the selectedSearch to set
	 */
	public void setSelectedSearch(int selectedSearch) {
		this.selectedSearch = selectedSearch;
	}

	/**
	 * @return the titleFieldCriterion
	 */
	public String getTitleFieldCriterion() {
		return titleFieldCriterion;
	}

	/**
	 * @param titleFieldCriterion the titleFieldCriterion to set
	 */
	public void setTitleFieldCriterion(String titleFieldCriterion) {
		this.titleFieldCriterion = titleFieldCriterion;
	}

	/**
	 * @return the renderFieldCriterion
	 */
	public boolean isRenderFieldCriterion() {
		return renderFieldCriterion;
	}

	/**
	 * @param renderFieldCriterion the renderFieldCriterion to set
	 */
	public void setRenderFieldCriterion(boolean renderFieldCriterion) {
		this.renderFieldCriterion = renderFieldCriterion;
	}
	
	/**
	 * @return the selectedPlayer
	 */
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	/**
	 * @param selectedPlayer the selectedPlayer to set
	 */
	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
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
	 * Metodo para controle da seleção de pesquisa.
	 * Ao selecionar um tipo de pesquisa este será 
	 * invocado para definir regras de exibição.
	 * 
	 * Detalhes das alterações envolvidas no evento:
	 * itemLabel= (Todos) 	itemValue= 0
	 * itemLabel= Nome 		itemValue= 1
	 * itemLabel= E-mail 	itemValue= 2
	 * itemLabel= Telefone 	itemValue= 3
	 * itemLabel= Codinome 	itemValue= 4
	 * itemLabel= Grupo 	itemValue= 5
	 */
	public void onChangeOMSearch() {
		switch (selectedSearch) {		
			case 1:
				titleFieldCriterion = "Insira o Nome";
				renderFieldCriterion = true;
				break;
				
			case 2:
				titleFieldCriterion = "Insira o E-mail";
				renderFieldCriterion = true;
				break;
				
			case 3:
				titleFieldCriterion = "Insira o Telefone";
				renderFieldCriterion = true;
				break;
				
			case 4:
				titleFieldCriterion = "Insira o Codinome";
				renderFieldCriterion = true;
				break;
				
			case 5:
				titleFieldCriterion = "Insira o Grupo";
				renderFieldCriterion = true;
				break;

			default:
				titleFieldCriterion = "";
				renderFieldCriterion = false;
				break;
		}
	}
	
	/**
	 * Pesquisa um jogador na base de dados.
	 * 
	 * Significado de cada índice:
	 * itemLabel= (Todos) 	itemValue= 0
	 * itemLabel= Nome 		itemValue= 1
	 * itemLabel= E-mail 	itemValue= 2
	 * itemLabel= Telefone 	itemValue= 3
	 * itemLabel= Codinome 	itemValue= 4
	 * itemLabel= Grupo 	itemValue= 5
	 */
	public void searchPlayer() {
		if (players != null) {
			players.clear();
		}
		if (valueSearch == null) {
			valueSearch = "";
		}
		switch (selectedSearch) {		
			case 1:
				players = playerService.findByName(valueSearch);
				break;
				
			case 2:
				players = playerService.findByEmail(valueSearch);
				break;
				
			case 3:
				players = playerService.findByTelephone(valueSearch);
				break;
				
			case 4:
				players = playerService.findByCodename(valueSearch);
				break;
				
			case 5:
				players = playerService.findByGroup(valueSearch);
				break;
				
			default:
				players = playerService.findAll();
				break;
		}
	}
	
	/**
	 * Remove um jogador na base de dados.
	 */
	public void removePlayer() {
		if (selectedPlayer != null) {
			playerService.remove(selectedPlayer);
			players.remove(selectedPlayer);
		}
	}
	
	/**
	 * Volta para a tela de cadastro de jogadores.
	 * 
	 * @return redireciona para a página 'player_register'
	 */
	public String newPlayer() {
		return "PLAYER_REGISTER";
	}
}