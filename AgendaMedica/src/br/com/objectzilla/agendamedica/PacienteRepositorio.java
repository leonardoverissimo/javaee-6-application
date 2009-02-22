package br.com.objectzilla.agendamedica;

public interface PacienteRepositorio {

	Paciente getPaciente(long id) throws PacienteNaoEncontradoException;

}
