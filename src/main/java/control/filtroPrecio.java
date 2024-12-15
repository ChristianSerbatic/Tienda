package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Producto;
import servicio.ProductoDAO;

/**
 * Servlet implementation class filtroPrecio
 */
@WebServlet("/filtroPrecio")
public class filtroPrecio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filtroPrecio() {
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
		// TODO Auto-generated method stub
		if(request.getParameter("precio")!=null) {
		double precio = Double.parseDouble(request.getParameter("precio"));
		request.setAttribute("precio", precio);
		}
		
		if(request.getParameter("pesca")!=null && request.getParameter("esqui") == null) {
			int filtro = Integer.parseInt(request.getParameter("pesca"));
			try {
				ArrayList<Producto> listaProductosFiltrados = ProductoDAO.filtrarProductos(filtro);
				request.setAttribute("listaProductosFiltrados", listaProductosFiltrados);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if (request.getParameter("esqui") != null && request.getParameter("pesca")==null){
			int filtro = Integer.parseInt(request.getParameter("esqui"));
			try {
				ArrayList<Producto> listaProductosFiltrados = ProductoDAO.filtrarProductos(filtro);
				request.setAttribute("listaProductosFiltrados", listaProductosFiltrados);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
