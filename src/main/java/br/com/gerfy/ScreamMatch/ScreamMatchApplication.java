package br.com.gerfy.ScreamMatch;

import br.com.gerfy.ScreamMatch.model.DadosSerie;
import br.com.gerfy.ScreamMatch.service.ConsumoAPI;
import br.com.gerfy.ScreamMatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreamMatchApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreamMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = 	consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=43454279");
		//Sstem.out.println(json);
		//json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}
