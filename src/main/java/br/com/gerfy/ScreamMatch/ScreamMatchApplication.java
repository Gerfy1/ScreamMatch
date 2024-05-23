package br.com.gerfy.ScreamMatch;

import br.com.gerfy.ScreamMatch.principal.Main;
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
		Main main = new Main();
		 main.exibirMenu();

	}
}
