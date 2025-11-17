package br.com.alura.tabelafipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehiclesData(@JsonAlias("Valor") String value,
                           @JsonAlias("Marca") String brand,
                           @JsonAlias("Modelo") String model,
                           @JsonAlias("AnoModelo") String year,
                           @JsonAlias("Combustivel") String fuel) {
}
