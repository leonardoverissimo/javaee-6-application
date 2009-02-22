package br.com.objectzilla.agendamedica;

import java.util.Calendar;

import javax.ejb.Stateless;

@Stateless
public class AgendamentoImpl implements Agendamento {

	@Override
	public void marcaConsulta(long medicoId, long pacienteId, Calendar horario) throws PacienteNaoEncontradoException, MedicoNaoEncontradoException {
		Paciente paciente = pacienteRepositorio.getPaciente(pacienteId);
		Medico medico = medicoRepositorio.getMedico(medicoId);
		
		medico.consulta(paciente, horario);
	}
	
	public void setPacienteRepositorio(PacienteRepositorio pacienteRep) {
		pacienteRepositorio = pacienteRep;
		
	}

	public void setMedicoRepositorio(MedicoRepositorio medicoRep) {
		medicoRepositorio = medicoRep;		
	}
	
	private PacienteRepositorio pacienteRepositorio;
	private MedicoRepositorio medicoRepositorio;
}
