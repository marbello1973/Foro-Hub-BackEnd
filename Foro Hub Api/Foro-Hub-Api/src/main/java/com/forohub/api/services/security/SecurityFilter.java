package com.forohub.api.services.security;

import com.forohub.api.modelo.usuarioautenticacion.UsuarioAutenticacionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioAutenticacionRepository usuarioAutenticacionRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            var token = authHeader.replace("Bearer", "").trim();
            var subject = tokenService.getSubject(token);
            if(subject != null){
                var usuario = usuarioAutenticacionRepository.findByEmail(subject).orElse(null);
                if(usuario != null){
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else{
                    System.out.println("Usuario no encontrado para el email: " + subject);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
