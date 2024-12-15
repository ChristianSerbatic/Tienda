package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import servicio.DetalleDAO;
/**
 * Servlet implementation class carritoUnidades
 */
@WebServlet("/carritoUnidades")
public class carritoUnidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carritoUnidades() {
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
		int idProducto = Integer.parseInt(request.getParameter("id"));
		String accion = request.getParameter("accion");
		
		if (accion.equals("aumentar")) {
			DetalleDAO.añadeUno(idProducto);
		} else if(DetalleDAO.unidadesDetalle(idProducto) >1)
		{
			DetalleDAO.reduceUno(idProducto);
		} else if(DetalleDAO.unidadesDetalle(idProducto) == 1){
			DetalleDAO.quitarProducto(idProducto);
		}
		
		
		request.getRequestDispatcher("carrito.jsp").forward(request, response);
	}

}
