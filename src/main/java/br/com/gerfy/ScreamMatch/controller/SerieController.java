package br.com.gerfy.ScreamMatch.controller;

import br.com.gerfy.ScreamMatch.dto.SerieDTO;
import br.com.gerfy.ScreamMatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servico;

    @GetMapping()
    public List<SerieDTO> obterSeries() {
        return servico.obterSeries();
    }
    @GetMapping("/top5")
    public List<SerieDTO> obterSerieTop5() {
        return servico.obterTop5Series();
    }
    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos(){
        return servico.obterLancamento();
    }
    @GetMapping("/{id}")
    public SerieDTO obterSeriePorId(@PathVariable Long id){
         return servico.obterPorId(id);
    }

}
