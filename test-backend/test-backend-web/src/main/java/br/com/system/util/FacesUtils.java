package br.com.system.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Classe util com funcionalidade do Primefaces.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
public class FacesUtils {

	/**
	 * Configura um objeto FacesMessage para exibição de mensagens na tela.
	 * 
	 * @param severity severidade da mensagem
	 * @param message mensagem
	 */
	private static FacesMessage getFacesMessage(Severity severity, String message) {
        FacesMessage facesMessage = new FacesMessage(severity, message, null);
        return facesMessage;
    }
	
	/**
	 * Exibe uma mensagem com nível de severidade: INFO.
	 * 
	 * @param message mensagem a ser exibida
	 */
    public static void successMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, getFacesMessage(FacesMessage.SEVERITY_INFO, message));
    }

    /**
	 * Exibe uma mensagem com nível de severidade: ERROR.
	 * 
	 * @param message mensagem a ser exibida
     */
    public static void errorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, getFacesMessage(FacesMessage.SEVERITY_ERROR, message));
    }
    
   /**
	 * Exibe uma mensagem com nível de severidade: WARN.
	 * 
	 * @param message mensagem a ser exibida
    */
   public static void warnMessage(String message) {
       FacesContext.getCurrentInstance().addMessage(null, getFacesMessage(FacesMessage.SEVERITY_WARN, message));
   }
     
    /**
	 * Exibe uma mensagem com nível de severidade: FATAL.
	 * 
	 * @param message mensagem a ser exibida
     */
    public static void fatalMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, getFacesMessage(FacesMessage.SEVERITY_FATAL, message));
    }
}