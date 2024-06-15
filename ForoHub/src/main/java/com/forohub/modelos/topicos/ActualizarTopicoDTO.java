package com.forohub.modelos.topicos;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String fecha,
        String autor

){}
