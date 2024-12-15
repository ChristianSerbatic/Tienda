<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Locale, java.util.ResourceBundle" %>

<%
    String language = request.getParameter("language");
    if (language != null) {
        if ("es".equals(language)) {
            
            request.getSession().setAttribute("lang", new Locale("es", "ES"));
        } else if ("en".equals(language)) {
            
            request.getSession().setAttribute("lang", new Locale("en", "US"));
        }
    }
    
    response.sendRedirect("login.jsp");
%>