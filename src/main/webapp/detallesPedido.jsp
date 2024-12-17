<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelo.Compra, servicio.CompraDAO, java.util.ArrayList, servicio.ProductoDAO, servicio.CarritoDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detalles del pedido</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/detallesPedido.css" type="text/css">	
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<div class="detallitos">
	<table class="table table-striped">
		<thead>
			<tr >
				<th class="text-center">Producto</th>
				<th class="text-center">Unidades</th>
				<th class="text-center">Precio Unidad</th>
				<th class="text-center">Total</th>
			</tr>
		</thead>
		<tbody>	
	<% 
		ArrayList<Compra> listaCompras = CompraDAO.mostrarCompras(); 
		int idPedido = (int)request.getAttribute("idPedido");
		
		double total = 0;
		for(Compra compra : listaCompras){
			if(compra.getPedido_id() == idPedido){
				total += compra.getTotal();
	%>
				<tr >
					<td><img src="imagenes/<%= ProductoDAO.recuperaImagen(compra.getProducto_id()) %>" alt="<%= compra.getProducto_id() %>" class="img-thumbnail ms-3"></td>
					<td ><%= compra.getUnidades() %></td>
					<td><%= compra.getPrecioUnidad() %></td>
					<td><%= compra.getTotal() %></td>
				</tr>				
	<% 
			}
		} 
	%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><%= total %></td>
				</tr>
		</tbody>
	</table>
	<a class="btn btn-outline-warning" href="personal.jsp">Volver al area personal</a>
	</div>
	
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>	
</body>
</html>