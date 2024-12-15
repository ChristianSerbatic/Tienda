package control;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servicio.UsuarioDAO;
import servicio.ClaveDAO;

/**
 * Servlet implementation class loguear
 */
@WebServlet("/loguear")
public class loguear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loguear() {
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
		
		HttpSession sesion = request.getSession();
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		// Cambia el idioma 
		request.getSession().setAttribute("lang", new Locale("es", "ES"));
		
		
		try {
			if(!UsuarioDAO.revisarAcceso(email, clave) ) {
				request.setAttribute("acceso", "Email o clave incorrectos");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else if (UsuarioDAO.revisarAcceso(email, clave) && sesion.getAttribute("origen").equals("mirando")) {
				sesion.setAttribute("usuario", email);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else if (UsuarioDAO.revisarAcceso(email, clave) && sesion.getAttribute("origen").equals("comprando")){
				sesion.setAttribute("usuario", email);
				request.getRequestDispatcher("carrito.jsp").forward(request, response);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
