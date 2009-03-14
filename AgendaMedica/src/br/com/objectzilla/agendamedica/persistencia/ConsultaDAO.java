package br.com.objectzilla.agendamedica.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;

import br.com.objectzilla.agendamedica.dominio.Consulta;
import br.com.objectzilla.agendamedica.dominio.ConsultaRepositorio;
import br.com.objectzilla.agendamedica.dominio.Medico;

@Singleton
public class ConsultaDAO implements ConsultaRepositorio {

	private List<Consulta> consultas = new ArrayList<Consulta>();

	@Override
	public void marca(Consulta consulta) {
		consultas.add(consulta);
	}

	@Override
	public boolean existeConsultaPara(Medico medico, Date horario, int duracaoMinutos) {
		// TODO Auto-generated method stub
		return false;
	}

}
