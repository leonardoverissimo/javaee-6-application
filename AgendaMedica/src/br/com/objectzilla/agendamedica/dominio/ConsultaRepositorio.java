package br.com.objectzilla.agendamedica.dominio;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface ConsultaRepositorio {
	
	void marca(Consulta consulta);
	
	boolean existeConsultaPara(Medico medico, Date horario, int duracaoMinutos);
}
