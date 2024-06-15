package com.forohub.modelos.topicos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
       @NotBlank
       String titulo,
       @NotBlank
       String mensaje,
       @NotBlank
       String fecha,
       @NotNull
       Boolean status,
       @NotBlank
       String curso,
       @NotBlank
       String autor
){}
