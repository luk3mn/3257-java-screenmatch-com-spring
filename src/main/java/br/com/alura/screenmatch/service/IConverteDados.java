package br.com.alura.screenmatch.service;

// a classe ConverteDados que irá implementar o método descrito na mesma.
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
