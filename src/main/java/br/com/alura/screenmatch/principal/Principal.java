package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpsodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Epsodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a4fe987e";

    public void exibeMenu() {
        System.out.println("Digite um nome da série de busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        // convetendo os dados do temporada para a classe
        List<DadosTemporada> temporadas = new ArrayList<>();

        for(int i = 1; i<=dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
//        temporadas.forEach(System.out::println);


/*
//        for(int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpsodio> episodiosTemporada = temporadas.get(i).epsodios();
//            for(int j = 0; j< episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        // Usando lambda function no lugar dos "dois" for
        temporadas.forEach(t -> t.epsodios().forEach(e -> System.out.println(e.titulo())));

        // Forma completa
//        temporadas.forEach(t -> System.out.println(t));

        // forma simplificada
        temporadas.forEach(System.out::println);
*/



/*
        List<DadosEpsodio> dadosEpsodios = temporadas.stream()
                .flatMap(t -> t.epsodios().stream())
//                .toList(); // => operação final: lista imutavel
                .collect(Collectors.toList());

//        dadosEpsodios.add(new DadosEpsodio("teste", 3, "10", "2020-01-01"));
//        dadosEpsodios.forEach(System.out::println);
        System.out.println("\nTop 5 epsódios");
        dadosEpsodios.stream()
            .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(DadosEpsodio::avaliacao).reversed())
            .peek(e -> System.out.println("Ordenação" + e))
            .limit(5)
            .forEach(System.out::println);

        List<Epsodio> epsodios = temporadas.stream()
                .flatMap(t -> t.epsodios().stream()
                        .map(d -> new Epsodio(t.numero(), d))
                ).collect(Collectors.toList());

        epsodios.forEach(System.out::println);
*/




        List<Epsodio> episodios = temporadas.stream()
                .flatMap(t -> t.epsodios().stream()
                        .map(d -> new Epsodio(t.numero(), d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

/*
        System.out.println("Digite um trecho do título do episódio");
        var trechoTitulo = leitura.nextLine();
        Optional<Epsodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().contains(trechoTitulo))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado!");
        }
*/

/*
        System.out.println("A partir de que ano você deseja ver os episódios? ");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
            .forEach(e -> System.out.println(
                    "Temporada: " + e.getTemporada() +
                            " Episódio: " + e.getTitulo() +
                            " Data lançamento: " + e.getDataLancamento().format(formatador)
            ));
*/



        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Epsodio::getTemporada,
                        Collectors.averagingDouble(Epsodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);


        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Epsodio::getAvaliacao));

        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor Epsodio: " + est.getMax());
        System.out.println("Pior Epsodio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());



    }

}
