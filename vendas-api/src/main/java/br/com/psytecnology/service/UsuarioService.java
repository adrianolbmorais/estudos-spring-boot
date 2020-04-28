package br.com.psytecnology.service;

import br.com.psytecnology.domain.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UsuarioService extends UserDetailsService {
    Usuario criarUsuario(Usuario usuario);
    UserDetails autenticar(Usuario usuario);
}
