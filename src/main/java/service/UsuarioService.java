package service;

import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {

    public boolean verificarCredenciales(Usuario usuario, String password) {
        return usuario != null && BCrypt.checkpw(password, usuario.getPasswordHash());
    }

    public Usuario registrarUsuario(String username, String email, String password) {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordHash);
        return usuario;
    }
}
