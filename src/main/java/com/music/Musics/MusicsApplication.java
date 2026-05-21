package com.music.Musics;

import com.music.Musics.principal.Principal;
import com.music.Musics.services.ArtistaRepository;
import com.music.Musics.services.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicsApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;
	@Autowired
	private MusicaRepository musicaRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(MusicsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio, musicaRepositorio);
		principal.exibeMenu();
	}
}
