package br.com.gerfy.ScreamMatch.controller;

import br.com.gerfy.ScreamMatch.dto.SerieDTO;
import br.com.gerfy.ScreamMatch.model.Serie;
import br.com.gerfy.ScreamMatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> obterSeries() {
        return repository.findAll()
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getImgPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }
}
