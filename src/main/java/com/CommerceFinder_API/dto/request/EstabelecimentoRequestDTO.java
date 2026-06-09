package com.CommerceFinder_API.dto.request;

import com.CommerceFinder_API.enums.TipoEstabelecimento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstabelecimentoRequestDTO {

    @NotNull(message = "O nome do estabelecimento é obrigatório.")
    private String nome;

    @NotNull(message = "O tipo de categoaria é obrigatório.")
    private TipoEstabelecimento tipo; // "Bar", "Café", "Restaurante"

    private double notaAvaliacao;
    private double precoMin;
    private double precoMax;

    @NotNull(message = "Latitude é obrigatória.")
    private double latitude;

    @NotNull(message = "Longiture é obrigatória.")
    private double longitude;

    private String telefone;

}
