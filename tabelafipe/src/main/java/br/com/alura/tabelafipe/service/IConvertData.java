package br.com.alura.tabelafipe.service;

import java.util.List;

public interface IConvertData {

    //Para obter uma entidade do objeto
    <T> T getData(String json, Class<T> classe);

    //Para obter uma lista de entidades do objeto
    <T> List<T> getList(String json, Class<T> classe);
}
