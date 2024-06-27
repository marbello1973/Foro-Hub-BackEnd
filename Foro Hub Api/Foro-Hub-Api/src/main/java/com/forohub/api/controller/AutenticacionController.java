package com.forohub.api.controller;

import com.forohub.api.modelo.usuarioautenticacion.DatosAutenticacionUsuario;
import com.forohub.api.modelo.usuarioautenticacion.UsuarioAutenticacion;
import com.forohub.api.services.security.DatosJWToken;
import com.forohub.api.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.password()
        );
        var usuarioAutenticado = authenticationManager.authenticate(authentication);
        var JWTtoken = tokenService.generarToken((UsuarioAutenticacion) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(JWTtoken));
    }

}
