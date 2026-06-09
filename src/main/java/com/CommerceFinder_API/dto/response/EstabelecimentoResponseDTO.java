package com.CommerceFinder_API.dto.response;

import com.CommerceFinder_API.enums.TipoEstabelecimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstabelecimentoResponseDTO {

    private UUID id;
    private String nome;
    private TipoEstabelecimento tipo; // "Bar", "Café", "Restaurante"
    private double notaAvaliacao;
    private double precoMin;
    private double precoMax;
    private double latitude;
    private double longitude;
    private String telefone;
    private boolean estaAberto;
}
