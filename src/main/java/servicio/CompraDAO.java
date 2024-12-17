package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Compra;
import modelo.Detalle;

public class CompraDAO {

    private Connection conexion;
    private Statement statement;
    
    
    public static void insertarCompras(ArrayList<Compra> listaCompra) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		for(Compra compra : listaCompra) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("insert into compras ( pedido_id, producto_id, usuario_id, unidades, precioUnidad, total) values(?, ?, ?, ?, ?, ?)");

    			stmt.setInt(1, compra.getPedido_id());
    			stmt.setInt(2, compra.getProducto_id());
    			stmt.setInt(3, compra.getUsuario_id());
    			stmt.setInt(4, compra.getUnidades());
    			stmt.setDouble(5, compra.getPrecioUnidad());
    			stmt.setDouble(6, compra.getTotal());
    			
    			int i = stmt.executeUpdate();
  	            System.out.println(i + " compra insertada");
    			
    			conexion.commit();
	            
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		}
    		Conexion.desconectar();
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    public static ArrayList<Compra> mostrarCompras(){
      	Connection conexion = Conexion.getConexion();
      	ArrayList<Compra> listaCompras = new ArrayList<Compra>();
      	
    	if (conexion != null) {
            PreparedStatement statement;
			try {
				statement = conexion.prepareStatement("Select * from compras");
			             

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Compra c = new Compra();
                c.setId(rs.getInt("id"));
            	c.setPedido_id(rs.getInt("pedido_id"));
            	c.setProducto_id(rs.getInt("producto_id"));
            	c.setUsuario_id(rs.getInt("usuario_id"));
            	c.setUnidades(rs.getInt("unidades"));
            	c.setPrecioUnidad(rs.getDouble("precioUnidad"));
            	c.setTotal(rs.getDouble("total"));
            	listaCompras.add(c);
            }

            Conexion.desconectar();
            
			} catch (SQLException e) {
				e.printStackTrace();
			} 
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return listaCompras;
    }
}
