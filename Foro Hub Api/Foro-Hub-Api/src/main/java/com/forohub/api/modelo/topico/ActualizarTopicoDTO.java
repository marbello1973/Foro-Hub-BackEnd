package com.forohub.api.modelo.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        String autor,
        String curso
) {}
