package br.com.gerfy.ScreamMatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEP;
    private double avaliacao;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEP = dadosEpisodio.numeroEP();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());

        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataDeLancamento = LocalDate.parse(dadosEpisodio.dataDeLancamento());
        } catch (DateTimeParseException e) {
            this.dataDeLancamento = null;
        }
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getNumeroEP() {
        return numeroEP;
    }

    public void setNumeroEP(Integer numeroEP) {
        this.numeroEP = numeroEP;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    private LocalDate dataDeLancamento;

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEP=" + numeroEP +
                ", avaliacao=" + avaliacao +
                ", dataDeLancamento=" + dataDeLancamento +
                ' ';
    }
}
