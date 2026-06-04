// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 24/05/2026 | Modificación: 28/05/2026
// Descripción: Superclase abstracta base para la gestión de miembros del hogar.
// ==============================================================================
package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private ArrayList<String> misDispositivos; 
    private ArrayList<String> misOrganizaciones;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        this.misDispositivos = new ArrayList<>();
        this.misOrganizaciones = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public ArrayList<String> getMisDispositivos() { return misDispositivos; }
    public ArrayList<String> getMisOrganizaciones() { return misOrganizaciones; }
}