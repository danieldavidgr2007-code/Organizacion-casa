// ==============================================================================
// Universidad Distrital Francisco José de Caldas
// Autores: Cristian Velosa (20252020066) y Daniel David Granados Rivera (20252020135)
// ==============================================================================
package logica;

import persistencia.GestorArchivos;
import excepciones.PasswordInvalidaException;
import excepciones.UsuarioNoEncontradoException;
import java.util.ArrayList;

public class GestorUsuarios {
    
    private ArrayList<Usuario> listaUsuarios;

    public GestorUsuarios() {
        // Cargamos los usuarios del archivo
        this.listaUsuarios = GestorArchivos.cargarUsuarios();
        
        // ¡LA SOLUCIÓN! Si el archivo no existe o está vacío, creamos una lista nueva para que no estalle
        if (this.listaUsuarios == null) {
            this.listaUsuarios = new ArrayList<>();
        }
    }

    public boolean hayUsuariosRegistrados() {
        return !this.listaUsuarios.isEmpty();
    }

    // 1. CREATE
    public void registrarUsuario(Usuario nuevoUsuario) throws PasswordInvalidaException {
        if (!ValidadorSeguridad.esPasswordSegura(nuevoUsuario.getContrasena())) {
            throw new PasswordInvalidaException("La contraseña no cumple con los requerimientos de seguridad.");
        }
        listaUsuarios.add(nuevoUsuario);
        GestorArchivos.guardarUsuarios(listaUsuarios);
        System.out.println("[CONSOLE LOG] Usuario registrado -> " + nuevoUsuario.getNombreUsuario());
    }

    // 2. READ / LOGIN
    public Usuario buscarYAutenticar(String nombre, String passwordStr) throws UsuarioNoEncontradoException {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                if (u.verificarCredenciales(passwordStr)) {
                    return u;
                } else {
                    return null; // Contraseña incorrecta
                }
            }
        }
        throw new UsuarioNoEncontradoException("El usuario no existe.");
    }

    // Método para buscar un usuario por nombre (Para recuperación)
    public Usuario buscarUsuario(String nombre) throws UsuarioNoEncontradoException {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado.");
    }

    // Recuperación de cuenta validando preguntas
    public Usuario validarPreguntasSeguridad(String nombre, String r1, String r2, String r3) throws UsuarioNoEncontradoException {
        for (Usuario u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                boolean check1 = ValidadorSeguridad.limpiar(r1).equals(ValidadorSeguridad.limpiar(u.getRespSeguridad1()));
                boolean check2 = ValidadorSeguridad.limpiar(r2).equals(ValidadorSeguridad.limpiar(u.getRespSeguridad2()));
                
                if(check1 && check2) {
                    return u;
                } else {
                    return null;
                }
            }
        }
        throw new UsuarioNoEncontradoException("Usuario no encontrado.");
    }

    // 3. UPDATE
    public void actualizarPassword(Usuario u, String nuevaPass) throws PasswordInvalidaException {
        if (!ValidadorSeguridad.esPasswordSegura(nuevaPass)) {
            throw new PasswordInvalidaException("Nueva contraseña no segura.");
        }
        u.setContrasena(nuevaPass);
        GestorArchivos.guardarUsuarios(listaUsuarios);
    }
}