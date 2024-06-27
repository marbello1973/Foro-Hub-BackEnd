package com.forohub.api.controller;

import com.forohub.api.modelo.topico.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    @Transactional
    @Operation(
            summary = "Consultar todos los registros de topico",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<Page<TopicoDTO>> listadoTopico(@PageableDefault(size = 10) Pageable paginacion){

        Page<Topico> topicos = repository.findByStatusTrue(paginacion);
        if(topicos.isEmpty()){
            return ResponseEntity.ok(Page.empty(paginacion));
        }else {
            Page<TopicoDTO> topicoDTOS = topicos.map(TopicoDTO::new);
            return ResponseEntity.ok(topicoDTOS);
        }

    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Consultar registro por identificador unico ID",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<DatosRespuestaTopico> retornarRegistroUnico(@PathVariable Long id){
        Topico topico = repository.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
        return ResponseEntity.ok(datosTopicos);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registrar un topico en base de datos",
            tags = {"Topico Controller", "POST"}
    )
    public ResponseEntity<DatosRespuestaTopico> registroTopicos(@RequestBody DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = repository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datos = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualizar un topico",
            tags = {"Topico Controller", "PUT"}
    )
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody ActualizarTopicoDTO actualizarTopicoDTO){

        Topico topico = repository.findById(actualizarTopicoDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar com.forohub.api.modelo.topico.Topico con id: -> " + actualizarTopicoDTO.id()));

        if(actualizarTopicoDTO.id() == null) {
            throw new IllegalArgumentException("ID topico es requerido");
        }

        topico.actualizarDatos(actualizarTopicoDTO);
        repository.save(topico);

        return ResponseEntity.ok()
                .body(new DatosRespuestaTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaDeCreacion(),
                    topico.getStatus(),
                    topico.getAutor(),
                    topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Eliminar de forma logica un registro topico",
            tags = {"Topico Controller", "DELETE"}
    )
    public ResponseEntity<Topico> eliminarTopico(@PathVariable Long id){
        Topico topico = repository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}


/*//Page<TopicoDTO> topicoDTOS = repository.findByStatusTrue(paginacion).map(TopicoDTO::new);
        Page<Topico> topicos = repository.findByStatusTrue(paginacion);
        if(topicos.isEmpty()){
            return ResponseEntity.ok(Page.empty(paginacion));
        }else {
            Page<TopicoDTO> topicoDTOS = topicos.map(TopicoDTO::new);
            return ResponseEntity.ok(topicoDTOS);
        }
        //return ResponseEntity.ok(repository.findByStatusTrue(paginacion).map(TopicoDTO::new));*/