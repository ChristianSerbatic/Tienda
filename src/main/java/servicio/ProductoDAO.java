package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Detalle;
import modelo.Producto;

public class ProductoDAO {
    private Connection conexion;
    private Statement statement;
    
    
    public static ArrayList<Producto> mostrarProductos() throws SQLException{
      	Connection conexion = Conexion.getConexion();
      	ArrayList<Producto> listaProductos = new ArrayList<Producto>();
      	
    	if (conexion != null) {
            PreparedStatement statement = conexion.prepareStatement("Select * from producto");              

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
            	p.setNombre(rs.getString("nombre"));
            	p.setDescripcion(rs.getString("descripcion"));
            	p.setPrecio(rs.getDouble("precio"));
            	p.setImpuesto(rs.getDouble("impuesto"));
            	p.setStock(rs.getInt("stock"));
            	p.setBaja(rs.getInt("baja"));
            	p.setImagen(rs.getString("imagen"));
            	p.setCategoria(rs.getInt("categoria"));
            	listaProductos.add(p);
            }
    	
            
            Conexion.desconectar();
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return listaProductos;
    }
    
    public static Producto recuperarProducto(int id) {
    	Connection conexion = Conexion.getConexion();
    	Producto p = new Producto();
    	if (conexion != null) {
    		try {
	            PreparedStatement statement = conexion.prepareStatement("Select * from producto");              
	
	            ResultSet rs = statement.executeQuery();
	
	            while(rs.next()) {
	            	if(rs.getInt("id") == id) {
	            		p.setId(rs.getInt("id"));
	                	p.setNombre(rs.getString("nombre"));
	                	p.setDescripcion(rs.getString("descripcion"));
	                	p.setPrecio(rs.getDouble("precio"));
	                	p.setImpuesto(rs.getDouble("impuesto"));
	                	p.setStock(rs.getInt("stock"));
	                	p.setBaja(rs.getInt("baja"));
	                	p.setImagen(rs.getString("imagen"));
	                	p.setCategoria(rs.getInt("categoria"));
	            	}
	            }
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
    	
    	return p;
    }
    
    // Devuelve la imagen de un producto
    public static String recuperaImagen(int producto_id) {
    	Connection conexion = Conexion.getConexion();
    	String imagen = "";
    	
    	if (conexion != null) {
    		try {
	            PreparedStatement statement = conexion.prepareStatement("SELECT imagen FROM producto WHERE id = ?");              
	            statement.setInt(1, producto_id);
	            
	            ResultSet rs = statement.executeQuery();
	
	            while(rs.next()) {
	            	imagen = rs.getString("imagen");
	            	}
	            
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }

    	return imagen;
    }
    
    // Devuelve el nombre de un producto dado su id
    public static String recuperaNombre(int producto_id) {
    	Connection conexion = Conexion.getConexion();
    	String imagen = "";
    	
    	if (conexion != null) {
    		try {
	            PreparedStatement statement = conexion.prepareStatement("SELECT nombre FROM producto WHERE id = ?");              
	            statement.setInt(1, producto_id);
	            
	            ResultSet rs = statement.executeQuery();
	
	            while(rs.next()) {
	            	imagen = rs.getString("nombre");
	            	}
	            
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }

    	return imagen;
    }
    
    public static ArrayList<Producto> filtrarProductos(int categoria) throws SQLException{
      	Connection conexion = Conexion.getConexion();
      	ArrayList<Producto> listaProductos = new ArrayList<Producto>();
      	
    	if (conexion != null) {
            PreparedStatement statement = conexion.prepareStatement("Select * from producto WHERE categoria = ?");              
            statement.setInt(1, categoria);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
            	p.setNombre(rs.getString("nombre"));
            	p.setDescripcion(rs.getString("descripcion"));
            	p.setPrecio(rs.getDouble("precio"));
            	p.setImpuesto(rs.getDouble("impuesto"));
            	p.setStock(rs.getInt("stock"));
            	p.setBaja(rs.getInt("baja"));
            	p.setImagen(rs.getString("imagen"));
            	p.setCategoria(rs.getInt("categoria"));
            	listaProductos.add(p);
            }

            Conexion.desconectar();
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return listaProductos;
    }
}
