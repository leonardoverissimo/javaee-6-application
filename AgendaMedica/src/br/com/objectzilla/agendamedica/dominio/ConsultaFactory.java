package br.com.objectzilla.agendamedica.dominio;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ConsultaFactory {

	public Consulta novaConsulta(Paciente paciente, Medico medico, Date inicio, int duracaoMinutos) {
		boolean agendado = consultaRepositorio.existeConsultaPara(medico, inicio, duracaoMinutos);
		if (agendado) {
			throw new ConsultaNaoDisponivel();
		}
		
		return new Consulta(paciente, medico, inicio, duracaoMinutos);
	}
	
	@EJB
	public void setConsultaRepositorio(ConsultaRepositorio consultaRepositorio) {
		this.consultaRepositorio = consultaRepositorio;
	}

	private ConsultaRepositorio consultaRepositorio;
}
