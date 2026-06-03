// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// Fecha de Creación: 01/06/2026 | Modificación: 03/06/2026
// Descripción: Motor principal que gestiona las operaciones CRUD sobre la colección.
// ==============================================================================
package logica;
import persistencia.GestorArchivos;
import excepciones.PasswordInvalidaException;
import excepciones.UsuarioNoEncontradoException;
import java.util.ArrayList;

// [CRITERIO_CRUD_COMPLETAS]: Implementación independiente de Create, Read, Update, Delete.
public class GestorUsuarios {
    // [CRITERIO_COLECCIONES]: El sistema usa una colección para gestionar la casa.
    private ArrayList<Usuario> listaUsuarios;

    public GestorUsuarios() {
        this.listaUsuarios = GestorArchivos.cargarUsuarios();
    }

    public boolean hayUsuariosRegistrados() {
        return !listaUsuarios.isEmpty();
    }

    // 1. CREATE
    public void registrarUsuario(Usuario nuevoUsuario) throws PasswordInvalidaException {
        try {
            if (!ValidadorSeguridad.esPasswordSegura(nuevoUsuario.getContrasena())) {
                throw new PasswordInvalidaException("La contraseña no cumple con los requerimientos (Mayus, minus, num, especial, min 4).");
            }
            listaUsuarios.add(nuevoUsuario);
            GestorArchivos.guardarUsuarios(listaUsuarios);
            
            // [CRITERIO_POLIMORFISMO]: Polimorfismo al imprimir el rol del usuario recién creado.
            System.out.println("[CONSOLE LOG] CRUD-CREATE: Usuario registrado -> " + nuevoUsuario.getNombreUsuario());
            System.out.println("[CONSOLE LOG] INFO ROL: " + nuevoUsuario.obtenerRolYPermisos());
        } finally {
            System.out.println("[CONSOLE LOG] FINALLY: Intento de registro procesado.");
        }
    }

    // 2. READ / BUSCAR / LOGIN
    public Usuario buscarYAutenticar(String nombre, String passwordStr) throws UsuarioNoEncontradoException {
        try {
            for (Usuario u : listaUsuarios) {
                if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                    // Polimorfismo con la interfaz Autenticable
                    if (u.verificarCredenciales(passwordStr)) {
                        System.out.println("[CONSOLE LOG] CRUD-READ: Autenticación exitosa para: " + nombre);
                        return u;
                    } else {
                        System.out.println("[CONSOLE ERROR] CRUD-READ: Contraseña incorrecta para: " + nombre);
                        return null;
                    }
                }
            }
            throw new UsuarioNoEncontradoException("El usuario '" + nombre + "' no existe en el sistema.");
        } finally {
            System.out.println("[CONSOLE LOG] FINALLY: Operación de búsqueda/login finalizada.");
        }
    }

    // Método Auxiliar de Recuperación de cuenta (READ/VALIDATE)
    public Usuario validarPreguntasSeguridad(String nombre, String r1, String r2, String r3) throws UsuarioNoEncontradoException {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                boolean check1 = ValidadorSeguridad.limpiar(r1).equals(u.getRespSeguridad1());
                boolean check2 = ValidadorSeguridad.limpiar(r2).equals(u.getRespSeguridad2());
                boolean check3 = ValidadorSeguridad.limpiar(r3).equals(u.getRespSeguridad3());
                
                if(check1 && check2 && check3) {
                    System.out.println("[CONSOLE LOG] SEGURIDAD: Validación de 3 factores aprobada para: " + nombre);
                    return u;
                } else {
                    System.out.println("[CONSOLE ERROR] SEGURIDAD: Respuestas incorrectas en la recuperación.");
                    return null;
                }
            }
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado para recuperación.");
    }

    // 3. UPDATE
    public void actualizarPassword(Usuario u, String nuevaPass) throws PasswordInvalidaException {
        if (!ValidadorSeguridad.esPasswordSegura(nuevaPass)) {
            throw new PasswordInvalidaException("Nueva contraseña no segura.");
        }
        u.setContrasena(nuevaPass);
        GestorArchivos.guardarUsuarios(listaUsuarios);
        System.out.println("[CONSOLE LOG] CRUD-UPDATE: Contraseña actualizada con éxito en persistencia.");
    }

    // 4. DELETE
    public void eliminarUsuario(String nombre) throws UsuarioNoEncontradoException {
        Usuario objetivo = null;
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                objetivo = u;
                break;
            }
        }
        if (objetivo != null) {
            listaUsuarios.remove(objetivo);
            GestorArchivos.guardarUsuarios(listaUsuarios);
            System.out.println("[CONSOLE LOG] CRUD-DELETE: Usuario " + nombre + " purgado del sistema permanentemente.");
        } else {
            throw new UsuarioNoEncontradoException("No se puede eliminar. Usuario no hallado.");
        }
    }
}