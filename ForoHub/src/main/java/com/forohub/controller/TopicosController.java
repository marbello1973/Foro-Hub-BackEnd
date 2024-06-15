package com.forohub.controller;

import com.forohub.modelos.topicos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registro de topicos en base de datos",
            description = "",
            tags = {"Topico Controller", "POST"}
    )
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Topicos topicos = topicoRepository.save(new Topicos(datos));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getFecha(),
                topicos.getAutor()
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    @Transactional
    @Operation(
            summary = "Consulta los topicos en la base de datos",
            description = "",
            tags = {"Topico Controller", "GET"})
    public ResponseEntity<Page<TopicoDTO>> listadoTopicos (
            @PageableDefault(size = 20) Pageable paginacion
    ){
        //return medicoRepository.findAll(paginacion).map(MedicoDTO::new);
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(TopicoDTO::new));
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualizacion datos topicos",
            description = "",
            tags = {"Topico Controller", "PUT"})
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO
    ){
        Topicos topicos = topicoRepository.getReferenceById(actualizarTopicoDTO.id());
        topicos.actualizarDatos(actualizarTopicoDTO);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topicos.getTitulo(),
                topicos.getMensaje(),
                topicos.getAutor(),
                topicos.getFecha()
                ));
    }

    //Eliminado logico
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Consulta topicos desactivados en base de datos",
            description = "",
            tags = {"Topico Controller", "DELETE"})
    public ResponseEntity<Topicos> eliminarTopico(@PathVariable Long id){
        Topicos topicos = topicoRepository.getReferenceById(id);
        topicos.desactivarTopico();
        return ResponseEntity.noContent().build();
    }



}
