<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="servicio.ProductoDAO, modelo.Producto, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Tienda</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/style.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>

    <svg id="filtro" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg"><g id="_32" data-name="32"><path d="m13.5 7h-11a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1z"/><path d="m11.5 11h-7a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1z"/><path d="m9.5 15h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 1 0 1z"/></g></svg>
	<div id="formulario" style="display:none;">
	  <form action="filtroPrecio" method="POST" >
	  	<label>Precio</label><br>
		<input type="radio" id="50" name="precio" value="50">
		<label for="50">0-50€</label><br>
		<input type="radio" id="150" name="precio" value="150">
		<label for="150">0-150€</label><br>
		<input type="radio" id="javascript" name="precio" value="1000">
		<label for="1000">0-1000€</label><br><br>
		<label>Categoria</label><br>
		  <input type="checkbox" id="pesca" name="pesca" value="1">
		  <label for="pesca">Productos de pesca</label><br>
		  <input type="checkbox" id="esqui" name="esqui" value="2">
		  <label for="esqui">Productos de esqui</label><br><br>
	    <input type="submit" value="Filtrar"><input type="submit" value="Borrar filtros">
	  </form>
	</div>
	<script>
		let conDisplay = 0;
		window.onload = function() {
			  
			  const rectangulo = document.getElementById("filtro");
			  const formulario = document.getElementById("formulario");

			  // Añadir un evento de clic al rectángulo
			  rectangulo.addEventListener("click", function() {
			    if(conDisplay % 2 == 0){
			    formulario.style.display = "block";
				conDisplay++;
			    } else {
				    formulario.style.display = "none";
				    conDisplay++;
				    }
			  });
		}
	</script>
		
	<h1>TIENDA SERBATIC</h1>
	<% if(request.getAttribute("toastMensaje") != null){
	%>
		<div class="toast-container p-3">
	  		<div id="toast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
		    <div class="toast-body">
		      <%= request.getAttribute("toastMensaje") %>
		    </div>
		  </div>
		</div>
		<script>
		window.onload = function() {
		    var toastLive = document.getElementById('toast');
		    if (toastLive) {
		    	function showToast() {
		    	      toastLive.classList.add('show');
		    	      
		    	      setTimeout(function() {
		    	        toastLive.classList.remove('show');
		    	      }, 3000);
		    	    }
	    	    
		    	    showToast();
		    }
		}
		</script>
	<%	
	}	
	%>
	<section class="py-5">
    	<div class="container px-4 px-lg-5 mt-5">
        	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	<%  
		ArrayList<Producto> listaProductos = null;
		if(request.getAttribute("listaProductosFiltrados") != null){
			listaProductos = (ArrayList<Producto>)request.getAttribute("listaProductosFiltrados");
		} else {
			listaProductos = ProductoDAO.mostrarProductos(); }
	   HttpSession sesion = request.getSession();
	   sesion.setAttribute("origen", "mirando");
	   if(request.getAttribute("precio") == null){
		for(Producto producto : listaProductos){
	%>		
                <div class="col mb-5">
                    <div class="card h-100">
                        
                        <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                        <a href="detalleProducto?id=<%= producto.getId() %>">
                        	<img class="card-img-top" src="imagenes/<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>">
                        </a>
                        <div class="card-body p-4">
                            <div class="text-center">
                                
                                <h5 class="fw-bolder"><%= producto.getNombre() %></h5>
                                <div class="d-flex justify-content-center small text-warning mb-2">
									<p><%= producto.getDescripcion() %></p>
                                </div>
                                
                                <%= producto.getPrecio() %>€
                            </div>
                        </div>
                        
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        	<div class="text-center"><a class="btn btn-outline-dark mt-auto" href="aCarrito?id=<%= producto.getId() %>">Add to cart</a></div>
                    	</div>
                	</div>
            	</div>
	<%		
		}
	   } else {
		   double precio = (double) request.getAttribute("precio");
		   for(Producto producto : listaProductos){
		   	if(producto.getPrecio()<=precio){
	%>
		   		<div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                        <a href="detalleProducto?id=<%= producto.getId() %>">
                        	<img class="card-img-top" src="imagenes/<%= producto.getImagen() %>" alt="<%= producto.getNombre() %>">
                        </a>
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><%= producto.getNombre() %></h5>
                                <div class="d-flex justify-content-center small text-warning mb-2">
									<p><%= producto.getDescripcion() %></p>
                                </div>
                                
                                <%= producto.getPrecio() %>€
                            </div>
                        </div>
                        
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        	<div class="text-center"><a class="btn btn-outline-dark mt-auto" href="aCarrito?id=<%= producto.getId() %>">Add to cart</a></div>
                    	</div>
                	</div>
            	</div>
		   		
	<%   		
		   	}
		   }
	   }
	%>
	    	</div>
    	</div>
    </section>
	
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

