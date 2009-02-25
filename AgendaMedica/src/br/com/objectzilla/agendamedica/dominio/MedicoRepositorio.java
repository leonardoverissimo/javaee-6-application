package br.com.objectzilla.agendamedica.dominio;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MedicoRepositorio {
	
	Medico getMedico(long id) throws MedicoNaoEncontradoException;
	
	List<Medico> todosMedicos();
	
	void salvaConsultaMedico(Medico medico);
}
