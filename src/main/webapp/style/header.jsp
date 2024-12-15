  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Locale, java.util.ResourceBundle, idioma.LanguageUtil" %>
  <header class="p-4 bg-dark text-white">
    <div class="cabecera">
      <div class="d-flex justify-content-around align-items-center">
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="index.jsp" class="nav-link px-2 text-secondary"><%= LanguageUtil.getMessage("login.inicio", request) %></a></li>
        </ul>

        <div class="text-end d-flex justify-content-between align-items-center flex-row">
          <a href="carrito.jsp" class="btn btn-outline-secondary" style="margin-left: 0.2em" >
        	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
  				<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
			</svg>
          </a>
          <a class="btn btn-outline-light" href="login.jsp" style="margin-left: 0.2em" ><%= LanguageUtil.getMessage("login.login", request) %></a>
          <a class="btn btn-warning" href="registro.jsp" style="margin-left: 0.2em" ><%= LanguageUtil.getMessage("login.registro", request) %></a>
          <% if(request.getSession().getAttribute("usuario") != null) { %>
				<p class="ml-2" style="margin-bottom: 0px" ><%= LanguageUtil.getMessage("login.saludo", request) %> <%= request.getSession().getAttribute("usuario") %></p>
				<a href="personal.jsp" style="text-decoration: none; margin-left: 1em" class=" text-white"><%= LanguageUtil.getMessage("login.personal", request) %></a>
          <% } %>
       </div>
    </div>
  </header>