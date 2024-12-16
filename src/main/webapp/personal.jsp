<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelo.Usuario, servicio.UsuarioDAO, modelo.Pedido, java.util.ArrayList, servicio.CarritoDAO"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Area personal</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/personal.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<% HttpSession sesion = request.getSession();
	Usuario u = UsuarioDAO.devuelveUsuario((String)request.getSession().getAttribute("usuario")); %>
	<div class="personal">
		<form action="actualizarUsuario" method="POST">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="email">Email</label> <input type="email"
						class="form-control" id="email" name="email" value="<%= u.getEmail()%>">
				</div>
				<div class="form-group col-md-6">
					<label for="clave">Contraseña</label> <input type="password"
						class="form-control" id="clave" name="clave"
						value="<%= u.getClave()%>"> <label for="clave">Repite
						contraseña</label> <input type="password" class="form-control" id="clave2"
						name="clave2" value="<%= u.getClave()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="nombre">Nombre</label> <input type="text"
					class="form-control" id="nombre" name="nombre" value="<%= u.getNombre()%>">
			</div>
			<div class="form-group">
				<label for="apellidos">Apellidos</label> <input type="text"
					class="form-control" id="apellidos" name="apellidos"
					value="<%= u.getApellidos()%>">
			</div>
			<% if (request.getAttribute("errorCampo") != null) {
			%>
				<textarea disabled rows="<%= request.getAttribute("lineas") %>" cols="40"  style="color:red; border:none; resize: none; background-color:white; overflow:hidden"><%= request.getAttribute("errorCampo") %></textarea>
			<%	
			} %>
		   <button type="submit" class="btn btn-warning">Actualizar</button>
		</form> 
		<table class="table table-striped ">
		  <caption class="table-caption" >Historial de pedidos</caption>
			<thead>
				<tr>
				<th class="text-center">Fecha Pedido</th>
				<th class="text-center">Total</th>
				<th class="text-center"></th>
				</tr>
			</thead>
			<tbody>
			<% 
					ArrayList<Pedido> listaPedidos = CarritoDAO.mostrarPedidos();
					for(Pedido p : listaPedidos){
						if(p.getUsuario_id() == UsuarioDAO.recuperaId((String)request.getSession().getAttribute("usuario"))){
			%>
					<tr>
						<td class="text-center"><%= p.getFecha() %></td>
						<td class="text-center"><%= p.getTotal() %></td>
						<td class="text-center"><a class=" text-warning" href="" >Mostrar detalles</a></td>
					</tr>	
			<%		
						}
					}
			%>
			</tbody>
		</table> 
	</div>
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>