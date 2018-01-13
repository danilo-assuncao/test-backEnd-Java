package br.com.system.exception;

import javax.ejb.ApplicationException;

/**
 * Classe para controle de exceções. Em caso de exceção 
 * deve realizar automáticamente o rollback.
 * 
 * @author dassuncao
 * @since test-backend 1.0
 */
@ApplicationException(rollback = true)
public class PlayerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PlayerException() {
		super();
	}

	public PlayerException(Throwable throwable) {
		super(throwable);
	}
}