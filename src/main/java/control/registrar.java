package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import servicio.UsuarioDAO;
import servicio.ClaveDAO;

/**
 * Servlet implementation class registrar
 */
@WebServlet("/registrar")
public class registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int lineas = 0;
		String errorCampo = "";
		HttpSession sesion = request.getSession();
		
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String clave2 = request.getParameter("clave2");

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		
		
		
		if(!comprobarCampo(nombre) || !comprobarCampo(apellidos) || !comprobarCampo(email) || !comprobarCampo(clave)) {
		
			if(!comprobarCampo(nombre)) {
				errorCampo += "Debe proporcionar un nombre\n";
				lineas ++;
			}
			
			if (!comprobarCampo(apellidos)) {
				errorCampo += "Debe proporcionar unos apellidos\n";
				lineas ++;
			} 
			
			if (!comprobarCampo(email)) {
				errorCampo += "Debe proporcionar un email valido\n";
				lineas ++;
			} 
			
			if (!comprobarCampo(clave)) {
				errorCampo += "Debe proporcionar una clave\n";
				lineas ++;
			}
			
			request.setAttribute("lineas", lineas);
			request.setAttribute("errorCampo", errorCampo);
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			
		} else if(UsuarioDAO.revisarEmail(email)) {
			errorCampo = "Email repetido";
			request.setAttribute("errorCampo", errorCampo);
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		} else if(!clave.equals(clave2)) {
			errorCampo = "La contraseña no es la misma";
			request.setAttribute("errorCampo", errorCampo);
			request.getRequestDispatcher("registro.jsp").forward(request, response);
			
		} else  if (!UsuarioDAO.revisarEmail(email) && sesion.getAttribute("origen").equals("mirando")){
			try {
				clave = ClaveDAO.claveHash(request.getParameter("clave"));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Usuario u = new Usuario(email, 2, clave, nombre, apellidos);
			UsuarioDAO.insertarUsuario(u);
			sesion.setAttribute("usuario", email);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			try {
				clave = ClaveDAO.claveHash(request.getParameter("clave"));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Usuario u = new Usuario(email, 2, clave, nombre, apellidos);
			UsuarioDAO.insertarUsuario(u);
			sesion.setAttribute("usuario", email);
			request.getRequestDispatcher("carrito.jsp").forward(request, response);	
		}

	}
	
	public boolean comprobarCampo(String campo) {
		boolean correcto = true;
		if (campo.isBlank() || campo.isEmpty()) {
			correcto = false;
		}
		
		return correcto;
	}

}
