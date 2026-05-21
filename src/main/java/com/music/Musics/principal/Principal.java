package com.music.Musics.principal;

import com.music.Musics.modelos.Artistas;
import com.music.Musics.modelos.Musicas;
import com.music.Musics.modelos.TipoArtista;
import com.music.Musics.services.ArtistaRepository;
import com.music.Musics.services.ConsumoGemini;
import com.music.Musics.services.MusicaRepository;

import javax.crypto.spec.PSource;
import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ArtistaRepository repositorio;
    private MusicaRepository musicaRepositorio;
    private List<Artistas> artistas = new ArrayList<>();
    private List<Musicas> musicas = new ArrayList<>();

    public Principal(ArtistaRepository repositorio, MusicaRepository musicaRepositorio) {this.repositorio = repositorio;this.musicaRepositorio = musicaRepositorio;}

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    
                                  ************************************
                                  *       SCREEN SOUND MÚSICAS       *
                                  ************************************
                                  
                     MENU DE OPÇÕES:    
                1 - Cadastrar Artistas
                2 - Cadastrar Musicas
                3 - Listar Musicas
                4 - Listar Artistas
                5 - Buscar Musica por Artista
                6 - Pesquisar sobre Artista
                7 - Remover Artista
                8 - Remover Musica
                
                0 - Sair                                 
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicasBuscadas();
                    break;
                case 4:
                    listaArtistas();
                    break;
                case 5:
                    buscaMusicaporArtista();
                    break;
                case 6:
                    pesquisaSobre();
                    break;
                case 7:
                    removeArtista();
                    break;
                case 8:
                    removeMusica();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    //Definindo as funções
    private void cadastrarArtistas() {
        while (true) {
            System.out.println("\n=== CADASTRO DE ARTISTA ===");
            System.out.println("Informe o nome do artista:");
            var nomeArtista = leitura.nextLine();
            if (nomeArtista == null || nomeArtista.trim().isEmpty()) {
                System.out.println("O nome não pode estar vazio!");
                continue;
            }
            Optional<Artistas> listaArtista = repositorio.findByNomeContainingIgnoreCase(nomeArtista);

            if (listaArtista.isPresent()) {
                System.out.println("Artista " + nomeArtista + " ja está cadastrado!");
            } else {
                try {
                    System.out.println("Informe o tipo de artista (solo, dupla ou banda):");
                    var tipoArtista = leitura.nextLine();
                    TipoArtista tipoArtistaEnum = TipoArtista.fromString(tipoArtista);
                    Artistas artista = new Artistas(nomeArtista, tipoArtistaEnum);
                    repositorio.save(artista);
                    System.out.println("Artista salvo com sucesso!");

                } catch (Exception e) {
                    System.out.println("Erro ao salvar o artista " + e.getMessage());
                }
            }
            System.out.println("Deseja cadastrar outro artista? (S/N)");
            var resposta = leitura.nextLine();
            if (resposta.equalsIgnoreCase("n")){
                System.out.println("Voltando ao menu...");
                break;
            } else if (!resposta.equalsIgnoreCase("s")) {
                System.out.println("Opção invalida! Voltando ao menu...");
                break;
            }
        }
    }

    private void cadastrarMusicas() {
        listaArtistas();
        System.out.println();
        System.out.println("\nDeseja adicionar musica de que artista?");
        var nomeArtista = leitura.nextLine();
        Optional<Artistas> artistas = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        Artistas artista = artistas.get();
        if (artistas.isPresent()){
            System.out.println("Artista selecionado: "+artista.getNome());
            System.out.println("Informe o nome da musica de "+ artista.getNome());
            var nomeMusica = leitura.nextLine();
            if (nomeMusica == null || nomeMusica.trim().isEmpty()) {
                System.out.println("Nome da música não pode ser vazio!");
                return;
            }
            System.out.println("Informe o álbum (opcional, pressione Enter para pular):");
            var album = leitura.nextLine();
            if (album.trim().isEmpty()) {
                album = null;
            }
            try {
                Musicas musica = new Musicas(nomeMusica, album, artista);
                musicaRepositorio.save(musica);
                artista.adicionarMusica(musica);
                repositorio.save(artista);
                System.out.println("\n Música cadastrada com sucesso!");
                System.out.println("   Música: " + nomeMusica);
                System.out.println("   Artista: " + artista.getNome());
                if (album != null) System.out.println("   Álbum: " + album);
            } catch (Exception e) {
                System.out.println("Erro ao cadastrar a musica! "+ e.getMessage());
            }
        }
    }

    private void listarMusicasBuscadas() {
        musicas = musicaRepositorio.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(Musicas::getNome))
                .forEach(m ->
                        System.out.printf("\nMusica: %s - Album: %s",
                                m.getNome(), m.getAlbum()));
        System.out.println();
    }

    private void buscaMusicaporArtista() {
        listaArtistas();
        System.out.println();
        System.out.println("\nDigite o nome do artista: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artistas> artistaRep = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistaRep.isEmpty()){
            System.out.println("Artista "+ nomeArtista + " não encontrado");
        }
        Artistas artistas = artistaRep.get();
        List<Musicas> musicas = musicaRepositorio.findByArtistas(artistas);
        if (musicas.isEmpty()){
            System.out.println("O artista não possui musicas cadastradas!");
        } else {
            System.out.println("=".repeat(50));
            System.out.println("ARTISTA: "+artistas.getNome().toUpperCase());
            System.out.println("TIPO: "+artistas.getTipoArtista());
            System.out.println("=".repeat(50));
            System.out.println("\n MUSICAS CADASTRADAS:\n");
            for (int i = 0; i < musicas.size(); i++) {
                Musicas m = musicas.get(i);
                System.out.printf("  %d. %s%n", (i + 1), m.getNome());
                if (m.getAlbum() != null && !m.getAlbum().trim().isEmpty()) {
                    System.out.println("   💿 Álbum: " + m.getAlbum());
                }
                System.out.println();
                System.out.println("-".repeat(50));
            }
            System.out.println("Total de músicas: " + musicas.size());
        }
    }

    private void pesquisaSobre() {
        listaArtistas();
        System.out.println();
        System.out.println("\nDigite o nome do artista que deseja saber sobre: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artistas> artistaRep = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistaRep.isEmpty()){
            System.out.println("Artista "+ nomeArtista + " não encontrado");
        }
        Artistas artistas = artistaRep.get();
        var resposta = ConsumoGemini.obterDados(artistas.getNome());
        System.out.println(resposta);
    }

    private void removeArtista() {
        listaArtistas();
        System.out.println();
        System.out.println("\nDigite o nome do artista que deseja remover: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artistas> artistaRep = repositorio.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistaRep.isEmpty()){
            System.out.println("Artista "+ nomeArtista + " não encontrado");
        }
        try {

            Artistas artistas = artistaRep.get();
            System.out.println("Você removera: ");
            System.out.println(" Artista: "+artistas.getNome());
            System.out.println(" Tipo: "+artistas.getTipoArtista());
            System.out.println(" Musicas: "+artistas.getMusicas().size());
            System.out.println("\n Você confirma a exclusão? (S/N)");
            var confirmacao = leitura.nextLine();
            if (confirmacao.equalsIgnoreCase("s")){
                repositorio.delete(artistas);
                System.out.println("\n Artista "+artistas.getNome()+" e suas musicas foram removidas com sucesso");
            } else {
                System.out.println("Operação cancelada");
            }

        } catch (Exception e) {
            System.out.println("Erro ao remover artista: "+ e.getMessage());
        }
    }

    private void removeMusica() {
        listarMusicasBuscadas();
        System.out.println();
        System.out.println("\nQue musica deseja remover: ");
        var nomeMusica = leitura.nextLine();
        Optional<Musicas> musicaEncontrada = musicaRepositorio.findByNomeContainingIgnoreCase(nomeMusica);
        if (musicaEncontrada.isEmpty()){
            System.out.println("Musica "+ musicaEncontrada + " não encontrada");
        }
        try {
            Musicas musica = musicaEncontrada.get();
            System.out.println("Você removera:");
            System.out.println(" Musica: "+musica.getNome());
            System.out.println(" Album: "+musica.getAlbum());
            System.out.println("\n Você confirma a exclusão? (S/N)");
            var confirmacao = leitura.nextLine();
            if (confirmacao.equalsIgnoreCase("s")){
                musicaRepositorio.delete(musica);
                System.out.println("A musica "+musica.getNome()+" foi removida com sucesso");
            } else {
                System.out.println("Operação cancelada");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover a musica: "+ e.getMessage());
        }
    }

    private void listaArtistas(){
        artistas = repositorio.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artistas::getTipoArtista))
                .forEach(a ->
                        System.out.printf("\nArtista: %s - Tipo: %s",
                                a.getNome(), a.getTipoArtista()));
    }
}
