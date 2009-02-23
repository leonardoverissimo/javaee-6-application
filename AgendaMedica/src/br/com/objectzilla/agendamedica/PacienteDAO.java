package br.com.objectzilla.agendamedica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

@Singleton
public class PacienteDAO implements PacienteRepositorio {

	@Override
	public Paciente getPaciente(long id) throws PacienteNaoEncontradoException {
		Paciente p = todos.get(id);
		if (p == null) {
			throw new PacienteNaoEncontradoException();
		}
		return p;
	}
	
	@Override
	public List<Paciente> todosPacientes() {
		return new ArrayList<Paciente>(todos.values());
	}
	
	private Map<Long, Paciente> todos;

	

}
