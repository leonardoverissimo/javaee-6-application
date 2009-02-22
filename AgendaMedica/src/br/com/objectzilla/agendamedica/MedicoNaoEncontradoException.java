package br.com.objectzilla.agendamedica;

public class MedicoNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4174035626542496225L;

	public MedicoNaoEncontradoException() {
	}

	public MedicoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedicoNaoEncontradoException(String message) {
		super(message);
	}

	public MedicoNaoEncontradoException(Throwable cause) {
		super(cause);
	}

}
