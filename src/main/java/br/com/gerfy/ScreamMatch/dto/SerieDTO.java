package br.com.gerfy.ScreamMatch.dto;

import br.com.gerfy.ScreamMatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String imgPoster,
                       String sinopse) {
}
