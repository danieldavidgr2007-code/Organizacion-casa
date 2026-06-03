// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 28/05/2026 | Modificación: 02/06/2026
// Descripción: Kernel de persistencia. Serializa y Deserializa la colección completa.
// ==============================================================================
package persistencia;
import logica.Usuario;
import java.io.*;
import java.util.ArrayList;

public class GestorArchivos {
    private static final String RUTA = "datos_usuario.dat";

    // Guarda el ArrayList completo.
    public static void guardarUsuarios(ArrayList<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            oos.writeObject(lista);
            System.out.println("[CONSOLE LOG] KERNEL: Datos de (" + lista.size() + ") usuarios guardados en disco exitosamente.");
        } catch (IOException e) {
            System.out.println("[CONSOLE ERROR] KERNEL: Falla crítica al guardar datos. " + e.getMessage());
        }
    }

    // Carga el ArrayList. Si no existe, retorna lista vacía segura.
    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> cargarUsuarios() {
        File file = new File(RUTA);
        if (!file.exists()) {
            System.out.println("[CONSOLE LOG] KERNEL: Archivo no existe. Inicializando lista vacía de usuarios.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<Usuario> lista = (ArrayList<Usuario>) ois.readObject();
            System.out.println("[CONSOLE LOG] KERNEL: Base de datos cargada. Usuarios registrados: " + lista.size());
            return lista;
        } catch (Exception e) {
            System.out.println("[CONSOLE ERROR] KERNEL: Error de lectura. Retornando lista vacía. " + e.getMessage());
            return new ArrayList<>();
        }
    }
}