package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Producto;
import modelo.Detalle;
import servicio.ProductoDAO;
import servicio.DetalleDAO;

/**
 * Servlet implementation class aCarrito
 */
@WebServlet("/aCarrito")
public class aCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean productoYaAñadido = false;
		int id = Integer.parseInt(request.getParameter("id"));
		Producto p = new Producto();
		p = ProductoDAO.recuperarProducto(id);
		
		Detalle d = new Detalle();
		d.setProducto_id(p.getId());
		d.setPrecioUnidad(p.getPrecio());
		d.setImpuesto(p.getImpuesto());
		
		if(!DetalleDAO.existeDetalle(id)) {
			d.setUnidades(1);
			d.setTotal(d.getPrecioUnidad()*d.getUnidades());
			DetalleDAO.insertarDetalle(d);
		} else {
			DetalleDAO.añadeUno(id);
		}
		
		
		// Mensaje para el toast
		request.setAttribute("toastMensaje", p.getNombre() + " añadido al carrito");
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

