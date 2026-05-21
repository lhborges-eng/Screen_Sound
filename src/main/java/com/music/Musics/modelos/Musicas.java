package com.music.Musics.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String album;

    @ManyToOne
    @JoinColumn(name = "artistas_id")
    private Artistas artistas;

    public Musicas(String nome, String album, Artistas artistas) {
        this.nome = nome;
        this.album = album;
        this.artistas = artistas;
    }

    public Musicas() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Artistas getArtistas() {
        return artistas;
    }

    public void setArtistas(Artistas artistas) {
        this.artistas = artistas;
    }

    @Override
    public String toString() {
        return "Musicas{" +
                "nome='" + nome + '\'' +
                ", album='" + album + '\'' +
                ", artistas=" + artistas +
                '}';
    }
}
