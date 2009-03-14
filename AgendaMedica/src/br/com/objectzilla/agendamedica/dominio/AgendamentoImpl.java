package br.com.objectzilla.agendamedica.dominio;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.objectzilla.agendamedica.aplicacao.Agendamento;

@Stateless
public class AgendamentoImpl implements Agendamento {

	@Override
	public void marcaConsulta(long medicoId, long pacienteId, Date horario)
			throws PacienteNaoEncontradoException, MedicoNaoEncontradoException {
		Paciente paciente = pacienteRepositorio.getPaciente(pacienteId);
		Medico medico = medicoRepositorio.getMedico(medicoId);
		
		consultaRepositorio.marca(new Consulta(paciente, medico, horario, 60));
	}

	@EJB
	public void setPacienteRepositorio(PacienteRepositorio pacienteRep) {
		pacienteRepositorio = pacienteRep;

	}

	@EJB
	public void setMedicoRepositorio(MedicoRepositorio medicoRep) {
		medicoRepositorio = medicoRep;
	}
	
	@EJB
	public void setConsultaRepositorio(ConsultaRepositorio consultaRep) {
		consultaRepositorio = consultaRep;
	}
	

	private PacienteRepositorio pacienteRepositorio;
	private MedicoRepositorio medicoRepositorio;
	private ConsultaRepositorio consultaRepositorio;
}
