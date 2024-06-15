package com.forohub.modelos.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String fecha;
    private Boolean status;
    private String curso;
    private String autor;



    public Topicos(DatosRegistroTopico datos){
        this.autor = datos.autor();
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = String.valueOf(datos.fecha());
        this.status = datos.status();
        this.curso = datos.curso();

    }

    public void actualizarDatos(ActualizarTopicoDTO actualizarTopicoDTO) {
        if(actualizarTopicoDTO.autor() != null) this.autor = actualizarTopicoDTO.autor();
        if(actualizarTopicoDTO.titulo() != null)  this.titulo = actualizarTopicoDTO.titulo();
        if(actualizarTopicoDTO.mensaje() != null) this.mensaje = actualizarTopicoDTO.mensaje();
        if(actualizarTopicoDTO.fecha() != null) this.mensaje = actualizarTopicoDTO.fecha();

    }

    public void desactivarTopico() {
        this.status = false;
    }

}
