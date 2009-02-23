package br.com.objectzilla.agendamedica;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Singleton;

@Singleton
public class MedicoDAO implements MedicoRepositorio {

	@Override
	public Medico getMedico(long id) throws MedicoNaoEncontradoException {
		Medico p = todos.get(id);
		if (p == null) {
			throw new MedicoNaoEncontradoException();
		}
		
		return copiaObjeto(p);
	}
	
	@Override
	public void salvaConsultaMedico(Medico medico) {
		todos.put(medico.getId(), medico);
		
	}

	@Override
	public List<Medico> todosMedicos() {
		Collection<Medico> medicos = copiaObjeto(todos.values());
		return new ArrayList<Medico>(medicos);
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
