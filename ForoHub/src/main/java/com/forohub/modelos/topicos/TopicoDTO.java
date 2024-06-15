package com.forohub.modelos.topicos;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        String fecha,
        String autor
){
    public TopicoDTO(Topicos topicos){
        this(
                topicos.getId(),
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFecha(),
                topicos.getAutor()
        );
    }
}
