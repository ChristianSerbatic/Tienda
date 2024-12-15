<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelo.Empresa, servicio.EmpresaDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Contacto</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/contacto.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<% Empresa e = EmpresaDAO.mostrarEmpresa(); %>
	<div class="contacto">
		<div>
			<h3>Datos de la empresa</h3>
			<p><%= e.getNombre() %></p>
			<p>Cif: <%= e.getCif() %></p>
			<p>Direccion: <%= e.getDireccion() %></p>
			<p>Ciudad: <%= e.getCiudad() %></p>
			<p>Telefono: <%= e.getTelefono() %></p><br>
		</div>
		<form action="enviarCorreo" method="POST">
		  <h4>Consulta a la empresa</h4><br>
		  <label for="destinatario">Destinatario:</label><br>
		  <input type="text" id="destinatario" name="destinatario" value="serbaticTienda@email.com"><br>
		  
		  <label for="asunto">Asunto:</label><br>
		  <input type="text" id="asunto" name="asunto" required><br>
		  
		  <label for="cuerpo">Cuerpo del mensaje:</label><br>
		  <textarea id="cuerpo" name="cuerpo" rows="10" cols="50" required ></textarea><br>
		  
		  <input type="submit" value="Enviar">
		</form>
			<% if (request.getAttribute("noLogueado") != null) {
		  %>
		 		<p style="color:red" >Es necesario loguearse para enviar un email</p>
		  <%	
		  } else if (request.getAttribute("enviado") != null) { 
		  %>
		  		<p style="color:orange" >Email enviado</p>
		  <%
		  }
		  %>
	</div>

	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</body>
</html>