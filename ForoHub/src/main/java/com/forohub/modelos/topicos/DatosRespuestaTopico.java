package com.forohub.modelos.topicos;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        String fecha,
        String autor
){}
