package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Producto;
import modelo.Detalle;

public class DetalleDAO {
	
    private Connection conexion;
    private Statement statement;
    
    public static ArrayList<Detalle> mostrarDetalles() throws SQLException{
      	Connection conexion = Conexion.getConexion();
      	ArrayList<Detalle> listaDetalles = new ArrayList<Detalle>();
      	
    	if (conexion != null) {
            PreparedStatement statement = conexion.prepareStatement("Select * from detalle");              

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Detalle d = new Detalle();
                d.setId(rs.getInt("id"));
            	d.setPedido_id(rs.getInt("pedido_id"));
            	d.setProducto_id(rs.getInt("producto_id"));
            	d.setUnidades(rs.getInt("unidades"));
            	d.setPrecioUnidad(rs.getDouble("precioUnidad"));
            	d.setImpuesto(rs.getDouble("impuesto"));
            	d.setTotal(rs.getDouble("total"));
            	listaDetalles.add(d);
            }

            Conexion.desconectar();
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return listaDetalles;
    }
	
    // Devuelve true si existe un detalle en base de datos con el id_Producto que se pasa como parámetro
    public static boolean existeDetalle(int id) {
    	boolean existe = false;
    	
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
	            PreparedStatement statement = conexion.prepareStatement("Select * from detalle");              
	
	            ResultSet rs = statement.executeQuery();
	
	            while(rs.next()) {
	                if(rs.getInt("producto_id") == id) {
	                	existe = true;
	                }
	            }
	
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return existe;
    }
    
    // Añade una nueva unidad al detalle cuyo producto_id se pasa por parámetro
    public static void añadeUno (int id) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("UPDATE detalle SET unidades = unidades + 1 WHERE producto_id = ?");

    			stmt.setInt(1, id);
    			
    		    int i = stmt.executeUpdate();
  	            System.out.println(i + " unidad aumentada");

    			
    			PreparedStatement stmt2 = conexion.prepareStatement("UPDATE detalle SET total = unidades * precioUnidad WHERE producto_id = ?");

    			stmt2.setInt(1, id);
    			
    		    int j = stmt2.executeUpdate();
  	            System.out.println(j + " precio total actualizado");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    // Se inserta un nuevo Detalle sin el pedido_id
    public static void insertarDetalle(Detalle d) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("insert into detalle ( producto_id, unidades, precioUnidad, impuesto, total) values(?, ?, ?, ?, ?)");

    			stmt.setInt(1, d.getProducto_id());
    			stmt.setInt(2, d.getUnidades());
    			stmt.setDouble(3, d.getPrecioUnidad());
    			stmt.setDouble(4, d.getImpuesto());
    			stmt.setDouble(5, d.getTotal());
    			
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
    
    public static void eliminarDetalle (int id) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("DELETE FROM detalle WHERE id=?");

    			stmt.setInt(1, id);
    			
    		    int i = stmt.executeUpdate();
  	            System.out.println(i + " detalle eliminado");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    public static void eliminarTodo () {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("TRUNCATE TABLE detalle");
    			
    		    int i = stmt.executeUpdate();
  	            System.out.println(" Registros de la tabla detalle eliminados");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    public static int unidadesDetalle(int id) {
    	int unidades = 0;
    	
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
	            PreparedStatement statement = conexion.prepareStatement("Select * from detalle");              
	
	            ResultSet rs = statement.executeQuery();
	
	            while(rs.next()) {
	                if(rs.getInt("producto_id") == id) {
	                	unidades=rs.getInt("unidades");
	                }
	            }
	
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return unidades;
    }
    
    public static void reduceUno (int id) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("UPDATE detalle SET unidades = unidades - 1 WHERE producto_id = ?");

    			stmt.setInt(1, id);
    			
    		    int i = stmt.executeUpdate();
  	            System.out.println(i + " unidad disminuida");

    			
    			PreparedStatement stmt2 = conexion.prepareStatement("UPDATE detalle SET total = unidades * precioUnidad WHERE producto_id = ?");

    			stmt2.setInt(1, id);
    			
    		    int j = stmt2.executeUpdate();
  	            System.out.println(j + " precio total actualizado");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    
    public static void quitarProducto (int id) {
    	Connection conexion = Conexion.getConexion();
    	if (conexion != null) {
    		try {
    			PreparedStatement stmt = conexion.prepareStatement("DELETE FROM detalle WHERE producto_id=?");

    			stmt.setInt(1, id);
    			
    		    int i = stmt.executeUpdate();
  	            System.out.println(i + " detalle eliminado");
    			
    			conexion.commit();
	            Conexion.desconectar();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            
        } else {
            System.out.println("Conexion no realizada");
        }
    }
    

    
}

