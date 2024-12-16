package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Pattern;

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
		
		
		
		if(!comprobarCampo(nombre) || !comprobarCampo(apellidos) || !comprobarCampo(email) || !comprobarCampo(clave) || !validarNombre(nombre) || !validarNombre(apellidos) || !validarEmail(email)) {
		
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
			
			if (!validarNombre(nombre)) {
				errorCampo += "El nombre debe ser válido\n";
				lineas ++;
			}
			if (!validarNombre(apellidos)) {
				errorCampo += "El apellido debe ser válido\n";
				lineas ++;
			}
			if (!validarEmail(email)) {
				errorCampo += "El email debe ser válido\n";
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
	
    public static boolean validarNombre(String nombre) {
        // Regex para solo letras (incluye acentos y espacios)
        String nombreRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return Pattern.matches(nombreRegex, nombre);
    }
    
    public static boolean validarEmail(String email) {
        // Regex para validar email (string@string)
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

}
