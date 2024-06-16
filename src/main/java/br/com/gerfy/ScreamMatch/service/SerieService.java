package br.com.gerfy.ScreamMatch.service;

import br.com.gerfy.ScreamMatch.dto.SerieDTO;
import br.com.gerfy.ScreamMatch.model.Serie;
import br.com.gerfy.ScreamMatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterSeries() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }
    public List<SerieDTO> obterTop5Series(){
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }
    private List<SerieDTO> converteDados(List<Serie> series){
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getImgPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterLancamento() {
        return converteDados(repository.findTop5ByOrderByEpisodiosDataDeLancamentoDesc());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getImgPoster(), s.getSinopse());
        }
        return null;
    }
}
