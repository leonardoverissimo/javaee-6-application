package br.com.objectzilla.agendamedica.persistencia;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

import br.com.objectzilla.agendamedica.dominio.HorarioDisponivel;
import br.com.objectzilla.agendamedica.dominio.Medico;
import br.com.objectzilla.agendamedica.dominio.MedicoNaoEncontradoException;
import br.com.objectzilla.agendamedica.dominio.MedicoRepositorio;
import br.com.objectzilla.agendamedica.dominio.HorarioDisponivel.DiaSemana;

@Singleton
public class MedicoDAO implements MedicoRepositorio {
	
	public MedicoDAO() {
		todos = new HashMap<Long, Medico>();
		Medico m;
		
		m = new Medico();
		m.setId(1);
		m.setNome("Dr. Gregory House");
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 14, 00, 17, 00));
		todos.put(m.getId(), m);
		
		m = new Medico();
		m.setId(2);
		m.setNome("Dr. Jack Shephard");
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 14, 00, 17, 00));
		todos.put(m.getId(), m);
		
		m = new Medico();
		m.setId(3);
		m.setNome("Dr. Meredith Grey");
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEGUNDA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.TERCA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUARTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.QUINTA, 14, 00, 17, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 8, 00, 13, 00));
		m.adicioneDisponibilidade(HorarioDisponivel.getInstance(DiaSemana.SEXTA, 14, 00, 17, 00));
		todos.put(m.getId(), m);
	}

	@Override
	public Medico getMedico(long id) throws MedicoNaoEncontradoException {
		Medico p = todos.get(id);
		if (p == null) {
			throw new MedicoNaoEncontradoException();
		}
		
		return copiaObjeto(p);
	}
	
	@Override
	public List<Medico> todosMedicos() {
		return copiaObjeto(new ArrayList<Medico>(todos.values()));
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T copiaObjeto(T original) {
		// cópia de objeto
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(original);
			byte[] array = baos.toByteArray();
			
			ByteArrayInputStream bais = new ByteArrayInputStream(array);
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			T copia = (T) ois.readObject();
			
			return copia;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Map<Long, Medico> todos;
}
