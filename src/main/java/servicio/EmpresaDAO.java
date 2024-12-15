package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Empresa;

public class EmpresaDAO {
	
    private Connection conexion;
    private Statement statement;
    
    public static Empresa mostrarEmpresa(){
      	Connection conexion = Conexion.getConexion();
      	Empresa e = new Empresa();
      	
    	if (conexion != null) {
            PreparedStatement statement;
			try {
				statement = conexion.prepareStatement("Select * from configuracion");
             

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                e.setNombre(rs.getString("nombre"));
                e.setCif(rs.getString("cif"));
                e.setDireccion(rs.getString("direccion"));
                e.setCiudad(rs.getString("ciudad"));
                e.setTelefono(rs.getString("telefono"));
            }

			} catch (SQLException e1) {
				
				e1.printStackTrace();
			} 
			
            Conexion.desconectar();
            
        } else {
            System.out.println("Conexion no realizada");
        }
    	
    	return e;
    }

}
