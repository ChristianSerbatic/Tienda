package idioma;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;

public class LanguageUtil {

    public static String getMessage(String key, HttpServletRequest request) {
        
        Locale locale = (Locale) request.getSession().getAttribute("lang");
        
        if (locale == null) {
            locale = new Locale("es", "ES");  
        }

        
        ResourceBundle bundle = ResourceBundle.getBundle("idioma.mensajes", locale);
        
        
        return bundle.getString(key);
    }
}