<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, modelo.Detalle, servicio.DetalleDAO, servicio.ProductoDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Resumen</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/comprado.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<div class="compra">
	<% if(request.getAttribute("comprado") == null){ %>
	<table class="table table-striped ">
	<thead>
		<tr>
		<th class="text-center">Producto</th>
		<th class="text-center">Unidades</th>
		<th class="text-center">Total</th>
		</tr>
	</thead>
	<tbody>
	<% 
		HttpSession sesion = request.getSession();
		double total = 0;
		if(sesion.getAttribute("carrito") != null){
			ArrayList<Detalle> listaCarrito = DetalleDAO.mostrarDetalles();
			for(Detalle detalle : listaCarrito){
				total += detalle.getTotal();
	%>
			<tr>
				<td class="text-center"><%= ProductoDAO.recuperaNombre(detalle.getProducto_id()) %></td>
				<td class="text-center"><%= detalle.getUnidades() %></td>
				<td class="text-center"><%= detalle.getTotal() %></td>
			</tr>	
	<%		
			}
		}
	%>
			<tr>
				<td></td>
				<td></td>
				<td class="text-center"><%= total %></td>
			</tr>
	</tbody>
	</table>
	
	<div>
		<form action="finalizarCompra" METHOD="POST">
		    <input type="radio" id="tarjeta" name="pago" value="tarjeta">
			<label for="tarjeta">Pago con tarjeta</label>
			<input type="radio" id="paypal" name="pago" value="paypal">
			<label for="javascript">Pago con Paypal</label><br><br>
		  <input type="submit" class="btn btn-outline-warning" value="Finalizar Compra">
		</form>
	</div>
	<% }else{
	%>	
		<h2>Carrito comprado</h2>
		<a class="btn btn-outline-warning" href="index.jsp">Volver al inicio</a>
	<%
	} %>
	</div>
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>