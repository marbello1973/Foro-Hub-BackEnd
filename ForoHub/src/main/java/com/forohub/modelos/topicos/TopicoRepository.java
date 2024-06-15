package com.forohub.modelos.topicos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoRepository extends JpaRepository<Topicos, Long> {
    Page<Topicos> findByStatusTrue(Pageable paginacion);
    Boolean findStatusById(Long idTopico);
}
