package com.forohub.api.modelo.topico;

import java.time.LocalDate;

public record TopicoDTO(
         Long id,
         String titulo,
         String mensaje,
         LocalDate fechaDeCreacion,
         String autor,
         String curso
) {
    public TopicoDTO(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
