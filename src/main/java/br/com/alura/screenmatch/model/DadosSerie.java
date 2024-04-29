package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignora todos os jsons os dados não mapeados
public record DadosSerie( // para modelar os dados que desejamos representar na aplicação
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalTempordas,
        @JsonAlias("imdbRating") String avaliacao
) {
}
