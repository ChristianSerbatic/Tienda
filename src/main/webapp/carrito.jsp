<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelo.Producto, java.util.ArrayList, modelo.Detalle, servicio.DetalleDAO, servicio.ProductoDAO"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Carrito</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/carrito.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>
	<div class="listaCompra d-flex justify-content-center align-items-center">
	<table class="table table-borderless">
		<tbody>
	<%  HttpSession sesion = request.getSession();
		double total = 0;
		sesion.setAttribute("origen", "comprando");
		if(DetalleDAO.mostrarDetalles() != null){
		ArrayList<Detalle> listaCarrito = DetalleDAO.mostrarDetalles(); 
		sesion.setAttribute("carrito", listaCarrito);
		for(Detalle detalle : listaCarrito){
			total += detalle.getTotal();
	%>		
			<tr class="d-flex justify-content-evenly align-items-center">
				<td><h5><%= ProductoDAO.recuperaNombre(detalle.getProducto_id()) %></h5></td>
				<td class="d-flex justify-content-evenly align-items-center flex-row">Unidades: <%= detalle.getUnidades() %>
					<div class="d-flex justify-content-evenly align-items-center flex-column ml-2">
						<a href="carritoUnidades?id=<%= detalle.getProducto_id() %>&accion=aumentar">
							<svg id="Layer_1" style="height:12px; width:auto" enable-background="new 0 0 32 32" viewBox="0 0 32 32" xmlns="http://www.w3.org/2000/svg"><path d="m25.955 15h-8.955v-8.955c0-.553-.448-1-1-1s-1 .447-1 1v8.955h-8.955c-.552 0-1 .447-1 1s.448 1 1 1h8.955v8.955c0 .553.448 1 1 1s1-.447 1-1v-8.955h8.955c.552 0 1-.447 1-1s-.448-1-1-1z" fill="rgb(0,0,0)"/></svg>
						</a>
						<a href="carritoUnidades?id=<%= detalle.getProducto_id() %>&accion=disminuir" >
							<svg fill="none" style="height:12px; width:auto" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="m18 11h-12c-.55 0-1 .45-1 1s.45 1 1 1h12c.55 0 1-.45 1-1s-.45-1-1-1z" fill="rgb(0,0,0)"/></svg>
						</a>
					</div>
				</td>
				<td><img src="imagenes/<%= ProductoDAO.recuperaImagen(detalle.getProducto_id()) %>" alt="<%= detalle.getProducto_id() %>" class="img-thumbnail ms-3"></td>
				<td>Precio: <%= detalle.getTotal() %></td>
				<td><a class="btn btn-danger" href="eliminarProducto?id=<%= detalle.getId() %>" >Eliminar producto</a></td>
			</tr>
	<%		
		}
	%>
	
	<% if (listaCarrito.size()==0){ %>
		
			<tr class="d-flex justify-content-evenly align-items-center" >
				<td></td>
				<td></td>
				<td><p>No existen productos en el carrito</p></td>
				<td></td>
				<td></td>
			</tr>
			<tr class="d-flex justify-content-around align-items-center w-75">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
	
	<% } else { %>
			<tr class="d-flex justify-content-evenly align-items-center" >
				<td></td>
				<td></td>
				<td></td>
				<td><p>Total: <%= total %></p></td>
				<td></td>
			</tr>
			<tr class="d-flex justify-content-around align-items-center w-75">
				<td></td>
				<td></td>
				<td></td>
				<td><a class="btn btn-warning" href="comprar" >Comprar</a></td>
				<td></td>
			</tr>
		<% 	} %>
	<%
	   }
	%>
		</tbody>
	</table>
	<% if (request.getAttribute("stock") != null) {
	%>
		<p style="color:red" >No hay suficiente stock</p>
	<%	
	} %>
	</div>
	<%@ include file="style/footer.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>