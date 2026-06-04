// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// ==============================================================================
package logica;

import java.io.*;
import java.util.ArrayList;

public class GestorUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Usuario> listaUsuarios;
    private static final String ARCHIVO_DATOS = "usuarios_smarthome.dat";

    public GestorUsuarios() {
        this.listaUsuarios = new ArrayList<>();
        cargarDatos();
    }

    public boolean hayUsuariosRegistrados() {
        return !listaUsuarios.isEmpty();
    }

    public boolean registrarUsuario(String username, String password) {
        if (obtenerUsuario(username) != null) {
            return false; // Ya existe ese nombre de usuario
        }
        listaUsuarios.add(new Usuario(username, password));
        guardarDatos();
        return true;
    }

    public boolean autenticar(String username, String password) {
        Usuario u = obtenerUsuario(username);
        return u != null && u.getPassword().equals(password);
    }

    public Usuario obtenerUsuario(String username) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(listaUsuarios);
            System.out.println("[DATA LOG] Datos guardados con éxito.");
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar la data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        File file = new File(ARCHIVO_DATOS);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                this.listaUsuarios = (ArrayList<Usuario>) ois.readObject();
                System.out.println("[DATA LOG] Datos cargados correctamente. Usuarios: " + listaUsuarios.size());
            } catch (Exception e) {
                System.out.println("[ERROR] Error al cargar la data, iniciando vacío.");
                this.listaUsuarios = new ArrayList<>();
            }
        }
    }
}