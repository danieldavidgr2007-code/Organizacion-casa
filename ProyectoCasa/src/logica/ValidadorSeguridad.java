// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 23/05/2026 | Modificación: 03/06/2026
// Descripción: Motor de limpieza de cadenas y validación Regex para seguridad.
// ==============================================================================
package logica;
import java.text.Normalizer;

public class ValidadorSeguridad {
    public static String limpiar(String texto) {
        if (texto == null) return "";
        String limpio = Normalizer.normalize(texto.trim().toLowerCase(), Normalizer.Form.NFD);
        return limpio.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static boolean esPasswordSegura(String pass) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.,]).{4,}$";
        return pass != null && pass.matches(regex);
    }
}