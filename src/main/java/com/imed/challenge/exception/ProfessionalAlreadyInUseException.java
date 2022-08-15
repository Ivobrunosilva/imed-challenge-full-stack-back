package com.imed.challenge.exception;

public class ProfessionalAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = 3995913950879568597L;

	public ProfessionalAlreadyInUseException(String mensagem) {
		super(mensagem);
	}

}
