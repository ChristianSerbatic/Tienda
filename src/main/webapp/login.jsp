<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Locale, java.util.ResourceBundle, idioma.LanguageUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="style/login.css" type="text/css">
</head>
<body>
	<%@ include file="style/header.jsp" %>

	<form id="lang" action="cambiarIdioma.jsp" method="POST">
	    <label for="language">Seleccionar idioma:</label>
	    <select name="language" onchange="this.form.submit()">
	        <option value="es" <%= request.getSession().getAttribute("lang") != null && "es".equals(((Locale) request.getSession().getAttribute("lang")).getLanguage()) ? "selected" : "" %>>Español</option>
	        <option value="en" <%= request.getSession().getAttribute("lang") != null && "en".equals(((Locale) request.getSession().getAttribute("lang")).getLanguage()) ? "selected" : "" %>>English</option>
	    </select>
	</form>

	<div class="form-container d-flex justify-content-center align-items-center ">
		<form action="loguear" method="POST">
		  <div class="form-group">
		    <label for="email"><%= LanguageUtil.getMessage("login.email", request) %></label>
		    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="jlop@gmail.com">
		    <small id="emailHelp" class="form-text text-warning"><%= LanguageUtil.getMessage("login.aviso", request) %></small>
		  </div>
		  <div class="form-group">
		    <label for="clave"><%= LanguageUtil.getMessage("login.clave", request) %></label>
		    <input type="password" class="form-control" id="clave" name="clave" placeholder="*****">
		  </div>
		  <% if (request.getAttribute("acceso") != null) {
		  %>
		 		<p style="color:red" ><%= request.getAttribute("acceso") %></p>
		  <%	
		  } %>
		  <button type="submit" class="btn btn-warning"><%= LanguageUtil.getMessage("login.acceso", request) %></button>
		</form>	
	</div>
	<%@ include file="style/footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>