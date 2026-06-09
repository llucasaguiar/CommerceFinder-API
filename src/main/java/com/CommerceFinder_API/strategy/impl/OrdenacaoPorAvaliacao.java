package com.CommerceFinder_API.strategy.impl;

import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.strategy.OrdenacaoStrategy;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrdenacaoPorAvaliacao implements OrdenacaoStrategy {

    @Override
    public List<Estabelecimento> ordenar(List<Estabelecimento> estabelecimentos, Double latitudeUsuario, Double longitudeUsuario) {
        return estabelecimentos.stream()
                .sorted(Comparator.comparing(Estabelecimento::getNotaAvaliacao).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public String obterNomeEstrategia() {
        return "avaliacao";
    }

}
