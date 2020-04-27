package br.com.psytecnology.rest.controller;

import br.com.psytecnology.domain.entity.Usuario;
import br.com.psytecnology.domain.entity.dto.CredenciaisDTO;
import br.com.psytecnology.domain.entity.dto.TokenDTO;
import br.com.psytecnology.exceptions.SenhaInvalidaException;
import br.com.psytecnology.security.jwt.JwtService;
import br.com.psytecnology.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.criarUsuario(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try{
            Usuario usuario = Usuario.builder()
                    .nomeUsuario(
                            credenciaisDTO.getLogin())
                    .senha(
                            credenciaisDTO.getSenha()
                    ).build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getNomeUsuario(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException exception){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }


    }

}
