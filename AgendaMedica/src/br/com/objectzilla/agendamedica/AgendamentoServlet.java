package br.com.objectzilla.agendamedica;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/agendamento")
public class AgendamentoServlet extends HttpServlet {
	
	@EJB
	private Agendamento agendamento;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		String nomeMedico = req.getParameter("medico");
		String nomePaciente = req.getParameter("paciente");
		int hora = Integer.parseInt(req.getParameter("hora"));
		
		// criando médico
		Medico medico = new Medico();
		medico.setNome(nomeMedico);
		
		// criando paciente
		Paciente paciente = new Paciente();
		paciente.setNome(nomePaciente);
		
		// criando horário
		Calendar horario = Calendar.getInstance();
		horario.set(Calendar.HOUR, hora);
		horario.set(Calendar.MINUTE, 0);
		horario.set(Calendar.SECOND, 0);
		
		// chamando método de agendamento
		agendamento.marcaConsulta(medico, paciente, horario);
		
		PrintWriter pw = resp.getWriter();
		pw.format("Eu juro que marquei consulta para o paciente %s, com o médico %s, na hora %d.",
				paciente.getNome(), medico.getNome(), horario.get(Calendar.HOUR));
		
		
	}
}
