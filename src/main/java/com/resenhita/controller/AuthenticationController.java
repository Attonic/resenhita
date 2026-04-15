package com.resenhita.controller;

import com.resenhita.config.secutiry.AuthenticationDto;
import com.resenhita.dto.UsuarioDto;
import com.resenhita.model.entity.Usuario;
import com.resenhita.repository.UsuarioRepository;
import com.resenhita.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioDto dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getSenha());

        Usuario novoUsuario = new Usuario(dto.getNome(), dto.getEmail(), encryptedPassword);
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
