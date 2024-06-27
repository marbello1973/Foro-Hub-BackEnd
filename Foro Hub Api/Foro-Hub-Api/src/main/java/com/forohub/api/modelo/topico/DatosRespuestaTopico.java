package com.forohub.api.modelo.topico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fechaDeCreacion,
        Boolean status,
        String autor,
        String curso
) {}
