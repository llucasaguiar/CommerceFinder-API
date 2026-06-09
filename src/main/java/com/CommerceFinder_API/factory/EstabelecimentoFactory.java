package com.CommerceFinder_API.factory;

import com.CommerceFinder_API.enums.TipoEstabelecimento;
import com.CommerceFinder_API.model.Bar;
import com.CommerceFinder_API.model.Cafe;
import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoFactory {

    public Estabelecimento criarEstabelecimento(TipoEstabelecimento tipo, String nome, Double lat, Double lng, String telefone) {
        Estabelecimento estabelecimento;

        switch (tipo) {
            case BAR -> estabelecimento = Bar.builder().nome(nome).tipo(tipo).latitude(lat).longitude(lng).notaAvaliacao(0.0).build();
            case CAFE -> estabelecimento = Cafe.builder().nome(nome).tipo(tipo).latitude(lat).longitude(lng).notaAvaliacao(5.0).build();
            case RESTAURANTE -> estabelecimento = Restaurante.builder().nome(nome).tipo(tipo).latitude(lat).longitude(lng).notaAvaliacao(4.0).build();
            default -> throw new IllegalArgumentException("Tipo de estabelecimento desconhecido: " + tipo);
        }

        estabelecimento.validarRegrasDeNegocio();

        return estabelecimento;
    }
}
