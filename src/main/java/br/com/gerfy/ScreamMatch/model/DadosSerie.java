package br.com.gerfy.ScreamMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias ("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Poster") String imgPoster,
                         @JsonAlias("Plot") String sinopse) {
    @Override
    public String toString() {
        return
                "Titulo: " + titulo + " | " +
                "Quantidade de temporadas: " + totalTemporadas + " | " +
                "Avaliação: " + avaliacao + " | " +
                "Genero: " + genero + " | " +
                "Atores: " + atores + " | " +
                "Poster promocional: " + imgPoster + " | " +
                "Sinopse: " + sinopse + " | ";
    }
}
