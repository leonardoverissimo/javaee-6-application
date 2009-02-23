package br.com.objectzilla.agendamedica;

import java.io.Serializable;

public class Paciente implements Serializable {
	
	private static final long serialVersionUID = 1768163639129128810L;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private long id;
	private String nome;

}
