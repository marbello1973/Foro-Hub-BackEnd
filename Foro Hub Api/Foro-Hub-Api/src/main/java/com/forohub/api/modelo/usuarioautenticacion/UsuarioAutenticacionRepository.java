package com.forohub.api.modelo.usuarioautenticacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioAutenticacionRepository extends JpaRepository<UsuarioAutenticacion, Long> {
    //UserDetails findByEmail(String username);
    Optional<UsuarioAutenticacion> findByEmail(String email);
}
