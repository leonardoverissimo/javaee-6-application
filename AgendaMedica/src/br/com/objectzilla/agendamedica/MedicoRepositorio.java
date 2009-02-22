package br.com.objectzilla.agendamedica;

public interface MedicoRepositorio {

	Medico getMedico(long id) throws MedicoNaoEncontradoException;

}
