package com.CommerceFinder_API.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ContextoOrdenacao {

    private final Map<String, OrdenacaoStrategy> estrategias = new HashMap<>();

    public ContextoOrdenacao(List<OrdenacaoStrategy> listaEstrategias) {
        listaEstrategias.forEach(estrategia ->
                estrategias.put(estrategia.obterNomeEstrategia().toLowerCase(), estrategia)
        );
    }

    public OrdenacaoStrategy obterEstrategia(String tipoOrdenacao) {
        return Optional.ofNullable(estrategias.get(tipoOrdenacao.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de ordenação não suportado: " + tipoOrdenacao));
    }
}
