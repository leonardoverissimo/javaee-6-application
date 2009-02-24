<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agenda M&eacute;dica</title>
</head>
<body>
	<jsp:useBean id="paciente" scope="request" type="br.com.objectzilla.agendamedica.Paciente"/>
	<jsp:useBean id="horario" scope="request" type="java.util.Calendar"/>
	
	<p>Data: <fmt:formatDate value="${horario.time}" type="both" dateStyle="long" timeStyle="medium"/></p>
	
	<p>Paciente: ${paciente.nome}</p>
	
	<a href="agendaForm">De novo</a>
</body>
</html>