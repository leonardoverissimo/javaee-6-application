package br.com.objectzilla.agendamedica.dominio;

public class ConsultaNaoDisponivel extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3949971738939041088L;

	public ConsultaNaoDisponivel() {
	}

	public ConsultaNaoDisponivel(String message, Throwable cause) {
		super(message, cause);
	}

	public ConsultaNaoDisponivel(String s) {
		super(s);
	}

	public ConsultaNaoDisponivel(Throwable cause) {
		super(cause);
	}
}
