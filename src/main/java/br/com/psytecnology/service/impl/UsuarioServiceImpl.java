package br.com.psytecnology.service.impl;

import br.com.psytecnology.domain.entity.Usuario;
import br.com.psytecnology.domain.repository.UsuarioRepository;
import br.com.psytecnology.exceptions.SenhaInvalidaException;
import br.com.psytecnology.service.UsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(@Lazy PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                                        .findByNomeUsuario(nomeUsuario)
                                        .orElseThrow(
                                            () -> new UsernameNotFoundException("Usuario não encontrado na base de dados")
                                        );
        String[] roles = usuario.isAdmin() ?
                new String[] {"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getNomeUsuario())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    @Override
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails autenticar(Usuario usuario) {
        UserDetails usuarioCadastrado = loadUserByUsername(usuario.getNomeUsuario());
        boolean senhasIguais = passwordEncoder.matches(usuario.getSenha(), usuarioCadastrado.getPassword());
        if(senhasIguais){
            return usuarioCadastrado;
        }
        throw new SenhaInvalidaException("Senha inválida.");
    }
}
