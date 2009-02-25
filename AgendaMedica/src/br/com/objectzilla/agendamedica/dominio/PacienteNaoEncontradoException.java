package br.com.objectzilla.agendamedica.dominio;

public class PacienteNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4146459153745250643L;

	public PacienteNaoEncontradoException() {
	}

	public PacienteNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PacienteNaoEncontradoException(String message) {
		super(message);
	}

	public PacienteNaoEncontradoException(Throwable cause) {
		super(cause);
	}
}
