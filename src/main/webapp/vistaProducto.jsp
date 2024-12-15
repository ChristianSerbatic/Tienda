<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelo.Producto, servicio.ProductoDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Producto</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/vistaProducto.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<% Producto p = ProductoDAO.recuperarProducto((int) request.getAttribute("id"));
	   HttpSession sesion = request.getSession();	
	   sesion.setAttribute("origen", "mirando");
	%>
	<div class="producto">
		<div>
			<img src="imagenes/<%= p.getImagen() %>" alt="<%= p.getNombre() %>" >
			<img src="imagenes/2_<%= p.getImagen() %>" alt="<%= p.getNombre() %>">
		</div>
		<p><%= p.getDescripcion() %></p>
		<p><%= p.getPrecio() %>€</p>
		<a class="btn btn-outline-warning" href="aCarrito?id=<%= p.getId() %>">Añadir al carrito</a>
	</div>

	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>