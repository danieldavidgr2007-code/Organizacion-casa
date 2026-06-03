// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 22/05/2026 | Modificación: 22/05/2026
// Descripción: Excepción personalizada para contraseñas que no cumplen el formato.
// ==============================================================================
package excepciones;

// [CRITERIO_EXCEPCIONES]: Creación de excepción propia heredando de Exception.
public class PasswordInvalidaException extends Exception {
    public PasswordInvalidaException(String mensaje) {
        super(mensaje);
    }
}