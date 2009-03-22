package br.com.objectzilla.agendamedica.aplicacao;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.model.ManagedBean;
import javax.faces.model.SessionScoped;

import br.com.objectzilla.agendamedica.dominio.ConsultaNaoDisponivel;
import br.com.objectzilla.agendamedica.dominio.Medico;
import br.com.objectzilla.agendamedica.dominio.MedicoNaoEncontradoException;
import br.com.objectzilla.agendamedica.dominio.MedicoRepositorio;
import br.com.objectzilla.agendamedica.dominio.Paciente;
import br.com.objectzilla.agendamedica.dominio.PacienteNaoEncontradoException;
import br.com.objectzilla.agendamedica.dominio.PacienteRepositorio;

@ManagedBean(name="consulta")
@SessionScoped
public class ConsultaManagedBean {
	
	public String pesquisa() {
		return "";
	}
	
	public String novo() {
		// preenche combo de médicos
		List<Medico> listaMedico = medicoRepositorio.todosMedicos();
//		medicos = new ListDataModel<Medico>(listaMedico);
		
		
		medicosItens = new LinkedHashMap<String, Long>();
		for (Medico m : listaMedico) {
			medicosItens.put(m.getNome(), m.getId());
			
		}
		
		// preenche combo de pacientes
		List<Paciente> listaPaciente = pacienteRepositorio.todosPacientes();
//		pacientes = new ListDataModel<Paciente>(listaPaciente);
		
		pacientesItens = new LinkedHashMap<String, Long>();
		for (Paciente p : listaPaciente) {
			pacientesItens.put(p.getNome(), p.getId());
		}
		
		return "formNovaConsulta";
	}
	
	public String agenda() {
		try {
			agendamento.marcaConsulta(medicoId, pacienteId, dia);
		} catch (PacienteNaoEncontradoException e) {
			mensagem = "Paciente não encontrado";
			return "formNovaConsulta";
		} catch (MedicoNaoEncontradoException e) {
			mensagem = "Médico não encontrado";
			return "formNovaConsulta";
		} catch (Exception e) {
			if (e.getCause().getClass().equals(ConsultaNaoDisponivel.class)) {
				mensagem = "Consulta não disponível";
			}
			return "formNovaConsulta";
		}
		
		return "mostraConsulta";
	}
	
	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
	
//	public DataModel<Medico> getMedicos() {
//		return medicos;
//	}
//
//	public void setMedicos(DataModel<Medico> medicos) {
//		this.medicos = medicos;
//	}
//
//	public DataModel<Paciente> getPacientes() {
//		return pacientes;
//	}
//
//	public void setPacientes(DataModel<Paciente> pacientes) {
//		this.pacientes = pacientes;
//	}
	
	public Map<String, Long> getMedicosItens() {
		return medicosItens;
	}

	public void setMedicosItens(Map<String, Long> medicosItens) {
		this.medicosItens = medicosItens;
	}
	
	public Map<String, Long> getPacientesItens() {
		return pacientesItens;
	}

	public void setPacientesItens(Map<String, Long> pacientesItens) {
		this.pacientesItens = pacientesItens;
	}

	public long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(long medicoId) {
		this.medicoId = medicoId;
	}

	public long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(long pacienteId) {
		this.pacienteId = pacienteId;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	@EJB
	private Agendamento agendamento;
	
	@EJB
	private MedicoRepositorio medicoRepositorio;
	
	@EJB
	private PacienteRepositorio pacienteRepositorio;
	
	private Date dia;
//	private DataModel<Medico> medicos;
//	private DataModel<Paciente> pacientes;
	private Map<String, Long> medicosItens;
	private Map<String, Long> pacientesItens;
	private long medicoId;
	private long pacienteId;
	private String mensagem;

}
