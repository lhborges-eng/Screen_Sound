package com.music.Musics.services;

import com.music.Musics.modelos.Artistas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artistas, Long>{
    Optional<Artistas> findByNomeContainingIgnoreCase(String nome);

}
