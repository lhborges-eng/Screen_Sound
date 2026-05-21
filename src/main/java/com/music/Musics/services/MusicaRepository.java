package com.music.Musics.services;

import com.music.Musics.modelos.Artistas;
import com.music.Musics.modelos.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musicas, Long> {
    List<Musicas> findByArtistas(Artistas artistas);
    Optional<Musicas> findByNomeContainingIgnoreCase(String nome);
}
