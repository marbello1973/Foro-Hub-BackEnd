package com.forohub.modelos.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String autor

){}
