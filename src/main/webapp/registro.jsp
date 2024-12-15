<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/login.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>

	<div class="form-container d-flex justify-content-center align-items-center">
		<form action="registrar" method="POST" >
  			<div class="form-row">
    			<div class="form-group col-md-6">
      				<label for="email">Email</label>
      				<input type="email" class="form-control" id="email" name="email" placeholder="Email" >
    			</div>
    			<div class="form-group col-md-6">
      				<label for="clave">Contraseña</label>
      				<input type="password" class="form-control" id="clave" name="clave" placeholder="Password" >
      				<label for="clave">Repite contraseña</label>
      				<input type="password" class="form-control" id="clave2" name="clave" placeholder="Password" >
    			</div>
  			</div>
  			<div class="form-group">
   				<label for="nombre">Nombre</label>
    			<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Pedro" >
  			</div>
  			<div class="form-group">
    			<label for="apellidos">Apellidos</label>
    			<input type="text" class="form-control" id="apellidos" name="apellidos" placeholder="Ramirez López"  >
  			</div>
  			<% if (request.getAttribute("errorCampo") != null) {
			%>
				<textarea disabled rows="<%= request.getAttribute("lineas") %>" cols="40"  style="color:red; border:none; resize: none; background-color:white; overflow:hidden"><%= request.getAttribute("errorCampo") %></textarea>
			<%	
			} %> 
  			<button type="submit" class="btn btn-warning">Registrar</button>
		</form> 					
	</div>

	
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>