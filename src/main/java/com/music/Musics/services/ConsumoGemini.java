package com.music.Musics.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsumoGemini {
    public static String obterDados(String nome) {
    Client client = Client.builder().apiKey(System.getenv("GEMINI_APIKEY")).build();

    GenerateContentResponse response =
            client.models.generateContent(
                    "gemini-3-flash-preview",
                    "Me de informações sobre esse artista musical "+nome+", busque o maior numero de informações em até 60 palavras, não pergunte mais nada, apenas faça",
                    null);

        return response.text();
    }
}
