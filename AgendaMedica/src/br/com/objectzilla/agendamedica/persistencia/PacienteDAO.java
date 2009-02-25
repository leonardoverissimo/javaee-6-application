package br.com.objectzilla.agendamedica.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import br.com.objectzilla.agendamedica.dominio.Paciente;
import br.com.objectzilla.agendamedica.dominio.PacienteNaoEncontradoException;
import br.com.objectzilla.agendamedica.dominio.PacienteRepositorio;

@Singleton
public class PacienteDAO implements PacienteRepositorio {
	
	public PacienteDAO() {
		todos = new HashMap<Long, Paciente>();
		Paciente p;
		
		p = new Paciente();
		p.setId(1);
		p.setNome("Adriana");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(2);
		p.setNome("Bruno");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(3);
		p.setNome("Camila");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(4);
		p.setNome("Daniel");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(5);
		p.setNome("Eliane");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(6);
		p.setNome("Felipe");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(7);
		p.setNome("Giovana");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(8);
		p.setNome("Henrique");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(9);
		p.setNome("Ingrid");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(10);
		p.setNome("Jairo");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(11);
		p.setNome("Kátia");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(12);
		p.setNome("Leonardo");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(13);
		p.setNome("Mariana");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(14);
		p.setNome("Nícolas");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(15);
		p.setNome("Olga");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(16);
		p.setNome("Pedro");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(17);
		p.setNome("Quitéria");
		todos.put(p.getId(), p);

		p = new Paciente();
		p.setId(18);
		p.setNome("Rafael");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(19);
		p.setNome("Sabrina");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(20);
		p.setNome("Tiago");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(21);
		p.setNome("Úrsula");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(22);
		p.setNome("Vitor");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(23);
		p.setNome("Wendy");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(24);
		p.setNome("Xavier");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(25);
		p.setNome("Yoná");
		todos.put(p.getId(), p);
		
		p = new Paciente();
		p.setId(26);
		p.setNome("Zilda");
		todos.put(p.getId(), p);

		
	}

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
