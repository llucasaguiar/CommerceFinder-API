package com.CommerceFinder_API.strategy.impl;

import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.strategy.OrdenacaoStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenacaoPorPreco implements OrdenacaoStrategy {

    @Override
    public List<Estabelecimento> ordenar(List<Estabelecimento> estabelecimentos, Double latitudeUsuario, Double longitudeUsuario) {
        return estabelecimentos.stream()
                .sorted(Comparator.comparing(Estabelecimento::getPrecoMin).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public String obterNomeEstrategia() {
        return "preco";
    }
}
