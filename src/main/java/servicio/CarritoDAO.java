package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Detalle;
import modelo.Pedido;

public class CarritoDAO {
	
    private Connection conexion;
    private Statement statement;
	
	public static boolean hayStock (ArrayList<Detalle> carrito) {
		boolean hayStock = true;
		int stock = 0;
		Connection conexion = Conexion.getConexion();
		for(Detalle detalle : carrito){
			if (conexion != null) {
	    		try {
	    			PreparedStatement st = conexion.prepareStatement("SELECT stock FROM producto WHERE id = ?");

	    			st.setInt(1, detalle.getProducto_id());
	    			
	    			ResultSet rs = st.executeQuery();
	    			
	    			while(rs.next()) {
		                stock = rs.getInt("stock");
		                
		            }
		    		if (stock < detalle.getUnidades()) {
		    			hayStock = false;
		    		}
		            
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	            
	        } else {
	            System.out.println("Conexion no realizada");
	        }
		}
		Conexion.desconectar();
		return hayStock;
	}
    
    public static void reducirStock (ArrayList<Detalle> carrito) {
    	Connection conexion = Conexion.getConexion();
		for(Detalle detalle : carrito){
			if (conexion != null) {
	    		try {
	    			PreparedStatement stmt = conexion.prepareStatement("UPDATE producto SET stock = stock - ? WHERE id = ?");

	    			stmt.setInt(1, detalle.getUnidades());
	    			stmt.setInt(2, detalle.getProducto_id());
	    			
	    		    int i = stmt.executeUpdate();
	  	            System.out.println(i + " stock actualizado");
	    			
	  	            conexion.commit();
		            
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	            
	        } else {
	            System.out.println("Conexion no realizada");
	        }
		}
		Conexion.desconectar();
    }
    
    public static void insertarPedido(Pedido p) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("insert into pedido ( usuario_id, fecha) values(?, ?)");

    			stmt.setInt(1, p.getUsuario_id());
    			stmt.setString(2, p.getFecha());
    			
    			int i = stmt.executeUpdate();
  	            System.out.println(i + " detalle insertado");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    public static ArrayList<Pedido> mostrarPedidos() throws SQLException{
      	Connection conexion = Conexion.getConexion();
      	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
      	
    	if (conexion != null) {
            PreparedStatement statement = conexion.prepareStatement("Select * from pedido");              

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
            	p.setUsuario_id(rs.getInt("usuario_id"));
            	p.setFecha(rs.getString("fecha"));
            	p.setTotal(rs.getDouble("total"));
            	listaPedidos.add(p);
            }

            Conexion.desconectar();
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return listaPedidos;
    }
    
    public static Pedido obtenerUltimoPedido() throws SQLException{
    	Pedido ultimoPedido = mostrarPedidos().get(mostrarPedidos().size() -1 );
    	return ultimoPedido;
    }
    
    public static void insertarMetodoPago(int idPedido, String metodoPago) {
    	Connection conexion = Conexion.getConexion();
			if (conexion != null) {
	    		try {
	    			PreparedStatement stmt = conexion.prepareStatement("UPDATE pedido SET metodopago = ? WHERE id = ?");
	    			stmt.setString(1, metodoPago);
	    			stmt.setInt(2, idPedido);
	    			
	    		    int i = stmt.executeUpdate();
	  	            System.out.println(i + " pedido actualizado");
	    			
	  	            conexion.commit();
		            
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	            
	        } else {
	            System.out.println("Conexion no realizada");
	        }
		
		Conexion.desconectar();
    }
    
}

