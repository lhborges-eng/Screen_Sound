package com.music.Musics.modelos;

public enum TipoArtista {
    SOLO,
    DUPLA,
    BANDA;

   public static TipoArtista fromString (String text) {
        for (TipoArtista tipoArtista : TipoArtista.values())
            if (tipoArtista.name().equalsIgnoreCase(text)) {
                return tipoArtista;
            }
        throw new IllegalArgumentException("Tipo Invalido!");
    }
}
