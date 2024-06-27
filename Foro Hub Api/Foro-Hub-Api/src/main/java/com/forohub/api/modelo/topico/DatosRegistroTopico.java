package com.forohub.api.modelo.topico;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        LocalDate fechaDeCreacion,
        @NotBlank
        Boolean status,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {}
