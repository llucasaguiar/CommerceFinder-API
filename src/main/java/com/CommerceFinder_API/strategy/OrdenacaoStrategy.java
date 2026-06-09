package com.CommerceFinder_API.strategy;

import com.CommerceFinder_API.model.Estabelecimento;

import java.util.List;

public interface OrdenacaoStrategy {

    List<Estabelecimento> ordenar(List<Estabelecimento> estabelecimentos, Double latitudeUsuario, Double longitudeUsuario);

    String obterNomeEstrategia();

}
