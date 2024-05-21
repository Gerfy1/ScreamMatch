package br.com.gerfy.ScreamMatch.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
