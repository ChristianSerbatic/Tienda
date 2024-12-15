package control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicio.ClaveDAO;
import servicio.UsuarioDAO;

/**
 * Servlet implementation class actualizarUsuario
 */
@WebServlet("/actualizarUsuario")
public class actualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorCampo = "";
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		String clave2 = request.getParameter("clave2");

		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		
		if(!clave.equals(clave2)) {
			System.out.println(clave + "     " + clave2);
			errorCampo = "La contraseña no es la misma";
			request.setAttribute("errorCampo", errorCampo);
			request.getRequestDispatcher("personal.jsp").forward(request, response);
			
		} else {
			try {
				clave = ClaveDAO.claveHash(request.getParameter("clave"));
			} catch (NoSuchAlgorithmException e) {
				
				e.printStackTrace();
			}
			UsuarioDAO.actualizarUsuario(email, clave, nombre, apellidos);
			errorCampo = "Usuario actualizado";
			request.getRequestDispatcher("personal.jsp").forward(request, response);
		}
	}

}
