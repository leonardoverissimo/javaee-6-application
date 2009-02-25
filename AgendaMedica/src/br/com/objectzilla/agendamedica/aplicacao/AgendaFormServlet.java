package br.com.objectzilla.agendamedica.aplicacao;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.objectzilla.agendamedica.dominio.Medico;
import br.com.objectzilla.agendamedica.dominio.MedicoRepositorio;
import br.com.objectzilla.agendamedica.dominio.Paciente;
import br.com.objectzilla.agendamedica.dominio.PacienteRepositorio;

@WebServlet(value="/agendaForm")
public class AgendaFormServlet extends HttpServlet {

	private static final long serialVersionUID = -7874706688461756994L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		// obtém médicos
		List<Medico> medicos = medicoRepositorio.todosMedicos();
		req.setAttribute("medicos", medicos);
		
		// obtém pacientes
		List<Paciente> pacientes = pacienteRepositorio.todosPacientes();
		req.setAttribute("pacientes", pacientes);
		
		// passa para uma página jsp
		req.getRequestDispatcher("/WEB-INF/agendaForm.jsp").forward(req, resp);
	}
	
	@EJB
	private MedicoRepositorio medicoRepositorio;
	
	@EJB
	private PacienteRepositorio pacienteRepositorio;
}
