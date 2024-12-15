package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Detalle;
import servicio.CarritoDAO;
import modelo.Pedido;
import servicio.UsuarioDAO;

/**
 * Servlet implementation class comprar
 */
@WebServlet("/comprar")
public class comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comprar() {
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
		ArrayList<Detalle> carrito =  (ArrayList<Detalle>) sesion.getAttribute("carrito");
		
		if(sesion.getAttribute("usuario") == null) {
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else if(!CarritoDAO.hayStock(carrito)){
			request.setAttribute("stock", "Sin stock");
			request.getRequestDispatcher("carrito.jsp").forward(request, response);
		} else {
			Pedido p = new Pedido (carrito);
			int id_usuario = UsuarioDAO.recuperaId((String)sesion.getAttribute("usuario"));
			p.setUsuario_id(id_usuario);
			CarritoDAO.reducirStock(carrito);
			CarritoDAO.insertarPedido(p);
			request.getRequestDispatcher("comprado.jsp").forward(request, response);
		}
		
	}

}
