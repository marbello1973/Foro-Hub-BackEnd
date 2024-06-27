package com.forohub.api.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.api.modelo.usuarioautenticacion.UsuarioAutenticacion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.services.security.secret}")//vienen del archivo .yml
    private String JWT_SECRET;

    public String generarToken(UsuarioAutenticacion usuarioAutenticacion){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(usuarioAutenticacion.getEmail())
                    .withClaim("id", usuarioAutenticacion.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if(token == null) throw new RuntimeException();
        DecodedJWT decodedJWT = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            decodedJWT = JWT.require(algorithm)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

        if (decodedJWT == null || decodedJWT.getSubject() == null) {
            throw new RuntimeException("Verifier invalid");
        }
        return decodedJWT.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
