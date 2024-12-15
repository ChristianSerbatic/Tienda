package servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import modelo.Usuario;
import control.HibernateUtil;

import servicio.ClaveDAO;

public class UsuarioDAO {
	
    private Connection conexion;
    private Statement statement;
    
    
    public static ArrayList<Usuario> mostrarUsuarios(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Usuario> query = session.createQuery("FROM Usuario", Usuario.class);
        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) query.list();
        session.close();
        return listaUsuarios;
    }
    
    public static boolean revisarEmail(String email) {
    	boolean emailRepetido = false;
    	ArrayList<Usuario> listaUsuarios = mostrarUsuarios();
    	for(Usuario usuario : listaUsuarios) {
    		if(usuario.getEmail().equals(email)) {
    			emailRepetido = true;
    		}
    	}
    	
    	return emailRepetido;
    } 
    
    public static void insertarUsuario(Usuario u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(u);
        transaction.commit();
        session.close();
    }
    
    public static boolean revisarAcceso(String email, String clave) throws Exception {
    	boolean acceso = false;
    	ArrayList<Usuario> listaUsuarios = mostrarUsuarios();
    	for(Usuario usuario : listaUsuarios) {
    		if(usuario.getEmail().equals(email) && ClaveDAO.verificarClave(clave, usuario.getClave())) {
    			acceso = true;
    		}
    	}
    	
    	return acceso;
    }
    
    public static int recuperaId(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT u.id FROM Usuario u WHERE u.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        int id = (int) query.uniqueResult();
        session.close();
        return id;
    }

    public static Usuario devuelveUsuario(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Usuario> query = session.createQuery("FROM Usuario WHERE email = :email", Usuario.class);
        query.setParameter("email", email);
        Usuario u = (Usuario) query.uniqueResult();
        session.close();
        return u;
    }
    
    public static void actualizarUsuario(String email, String clave, String nombre, String apellidos) {
    	
    	try {
    		Session session = HibernateUtil.getSessionFactory().openSession();
    		 Transaction transaction = session.beginTransaction();
    		Query<Usuario> query = session.createQuery("FROM Usuario WHERE email = :email", Usuario.class);
    		query.setParameter("email", email);
            Usuario u = (Usuario) query.uniqueResult();
            
       
            
            if (u != null) {
                
                u.setNombre(nombre);
                u.setApellidos(apellidos);
                u.setClave(clave);

                session.update(u);
                transaction.commit();
                session.close();
            }
        } catch (Exception e) {
        }
    }
}
