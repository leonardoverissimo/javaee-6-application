<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda M&eacute;dica</title>
</head>
<body>
	<jsp:useBean id="medicos" scope="request" type="java.util.List"/>
	<jsp:useBean id="pacientes" scope="request" type="java.util.List"/>
	
	<form method="POST" action="marcaConsulta">
		<p>Selecione o m&eacute;dico:
		<select size="1" name="medicoId">
			<option value="0">M&eacute;dicos</option>
			<c:forEach items="${medicos}" var="medico">
			<option value="${medico.id}">${medico.nome}</option>
			</c:forEach>
		</select>
		</p>
		<p>Selecione o paciente:
		<select size="1" name="pacienteId">
			<option value="0">Pacientes</option>
			<c:forEach items="${pacientes}" var="paciente">
			<option value="${paciente.id}">${paciente.nome}</option>
			</c:forEach>
		</select>
		</p>
		Dia: <input type="text" name="dia" size="10"><br/>
		Hora: <input type="text" name="hora" size="10"><br/>
		
		<p><input type="submit" value="Marcar Consulta"></p>
	</form>
</body>
</html>