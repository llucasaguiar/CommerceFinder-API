package com.CommerceFinder_API.strategy.impl;

import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.strategy.OrdenacaoStrategy;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenacaoPorDistancia implements OrdenacaoStrategy {

    @Override
    public List<Estabelecimento> ordenar(List<Estabelecimento> estabelecimentos, Double latitudeUsuario, Double longitudeUsuario) {
        if (latitudeUsuario == null || longitudeUsuario == null) {
            throw new IllegalArgumentException("Latitude e longitude do usuário são obrigatórias para ordenar por distância.");
        }
        return estabelecimentos.stream()
                .sorted(Comparator.comparing(e -> e.calcularDistanciaAte(latitudeUsuario, longitudeUsuario)))
                .collect(Collectors.toList());
    }

    @Override
    public String obterNomeEstrategia() {
        return "distancia";
    }

    public double calcularDistancia(double latUsuario, double lngUsuario, double latEstabelecimento, double lngEstablishment) {
        final int R = 6371; // Raio da Terra em KM

        double latDistancia = Math.toRadians(latEstabelecimento - latUsuario);
        double lngDistancia = Math.toRadians(lngEstablishment - lngUsuario);

        double a = Math.sin(latDistancia / 2) * Math.sin(latDistancia / 2)
                + Math.cos(Math.toRadians(latUsuario)) * Math.cos(Math.toRadians(latEstabelecimento))
                * Math.sin(lngDistancia / 2) * Math.sin(lngDistancia / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = R * c;
        return distancia;
    }

}
