package br.com.gerfy.ScreamMatch.principal;

import br.com.gerfy.ScreamMatch.model.*;
import br.com.gerfy.ScreamMatch.repository.SerieRepository;
import br.com.gerfy.ScreamMatch.service.ConsumoAPI;
import br.com.gerfy.ScreamMatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=43454279";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository serieRepositorio;

    private List<Serie> series = new ArrayList<>();
    private Optional<Serie> serieBusca;

    public Main(SerieRepository serieRepositorio) {
        this.serieRepositorio = serieRepositorio;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha dentre as seguintes opções:
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por titulo
                    5 - Buscar sério por ator
                    6 - 5 Melhores séries
                    7 - Buscar séries por categoria
                    8 - Filtrar temporadas e avaliação
                    9 - Buscar episódio por trecho
                    10 - Top 5 episódios por série
                    11 - Buscar episódios apartir de uma data
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscarSeriesPorCategoria();
                    break;
                case 8:
                    buscarTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpsodioPorTrecho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpsodiosApartirDeUmaData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        serieRepositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }
    private void buscarEpisodioPorSerie(){
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome:");
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serie = serieRepositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e))).collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            serieRepositorio.save(serieEncontrada);

        } else {
            System.out.println("Serie não localizada!");
        }
    }
    private void listarSeriesBuscadas(){
        series = serieRepositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    private void buscarSeriePorTitulo() {
        System.out.println("Digite o nome da Série:");
        var nomeSerie = leitura.nextLine();
        serieBusca = serieRepositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()){
            System.out.println("Dados da série: " +serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }
    private void buscarSeriesPorAtor() {
        System.out.println("Qual nome do ator?: ");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avaliações a partir de que valor?");
        var avaliacao = leitura.nextDouble();
        List <Serie> seriesEncontradas = serieRepositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Séries em que " +nomeAtor.toUpperCase() + " trabalhou com a avaliação acima de " +avaliacao+": ");
        seriesEncontradas.forEach((s -> System.out.println(s.getTitulo() + " avaliação: " +s.getAvaliacao())));
    }
    private void buscarTop5Series(){
        List <Serie> seriesTop = serieRepositorio.findTop5ByOrderByAvaliacaoDesc();
        seriesTop.forEach(s->System.out.println(s.getTitulo() + " avaliaçao: " +s.getAvaliacao()));
    }
    private void buscarSeriesPorCategoria() {
        System.out.println("Digite a categoria/gênero desejado: ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = serieRepositorio.findByGenero(categoria);
        System.out.println("Series da categoria " +nomeGenero + ":");
        seriesPorCategoria.forEach(System.out::println);
    }
    private void buscarTemporadaEAvaliacao() {
        System.out.println("Digite o número maximo de temporadas?: ");
        var numeroMaxTemp = leitura.nextInt();
        System.out.println("Qual numero mínimo de avaliação da série?: ");
        var numeroMinimoAv = leitura.nextInt();
        List<Serie> seriesFiltradas = serieRepositorio.seriesPorTemporadaEAvaliacao(numeroMaxTemp, numeroMinimoAv);
        System.out.println("Series filtradas: ");
        seriesFiltradas.forEach(s->System.out.println(s.getTitulo() + " | avaliação: " + s.getAvaliacao() + " | Temporadas: " + s.getTotalTemporadas()));
    }
    private void buscarEpsodioPorTrecho() {
        System.out.println("Qual é o trecho do episódio?: ");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosLocalizados = serieRepositorio.episodiosPorTrecho(trechoEpisodio);
        episodiosLocalizados.forEach(e -> System.out.printf("Série: %s | Temporada %s | Episódio %s | %s\n",
                                     e.getSerie().getTitulo(),e.getTemporada(),e.getNumeroEP(), e.getTitulo()));
    }
    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = serieRepositorio.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e->System.out.printf("Série: %s | Temporada %s | Episódio %s %s | Avaliação: %s\n",
                    e.getSerie().getTitulo(),e.getTemporada(),e.getNumeroEP(), e.getTitulo(), e.getAvaliacao()));
        }
    }
    private void buscarEpsodiosApartirDeUmaData() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            System.out.println("Digite a data limite de lançamento: ");
            var anoLançamento = leitura.nextInt();
            leitura.nextLine();
            List<Episodio> episodiosPorAno = serieRepositorio.episodiosPorSerieEAno(serie,anoLançamento);
            episodiosPorAno.forEach(System.out::println);

        }
    }
}