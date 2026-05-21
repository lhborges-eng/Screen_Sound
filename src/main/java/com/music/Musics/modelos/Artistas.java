package com.music.Musics.modelos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

    @OneToMany(mappedBy = "artistas", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Musicas> musicas = new ArrayList<>();

    public Artistas(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipoArtista = tipoArtista;
    }
    public Artistas() {
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setTipoArtista(TipoArtista tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public List<Musicas> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musicas> musicas) {
        this.musicas = musicas;
    }

    public void adicionarMusica(Musicas musica) {
        musicas.add(musica);
        musica.setArtistas(this);
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nome ='" + nome + '\'' +
                ", tipoArtista ='" + tipoArtista + '\'' +
                ", musicas ='" + musicas.size() + '\'' +
                '}';
    }
}
