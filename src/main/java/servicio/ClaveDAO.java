package servicio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.security.NoSuchAlgorithmException;

public class ClaveDAO {
	
    public static String claveHash(String clave) throws NoSuchAlgorithmException {
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	byte[] hashBytes = digest.digest(clave.getBytes());
    	
    	return Base64.getEncoder().encodeToString(hashBytes);
    }
    
    public static boolean verificarClave(String clave, String claveAlmacenada) throws Exception {
    	
        String hashClave = claveHash(clave);
        return hashClave.equals(claveAlmacenada);
    }
}