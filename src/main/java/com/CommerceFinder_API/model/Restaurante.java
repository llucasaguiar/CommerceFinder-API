package com.CommerceFinder_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("RESTAURANTE")
@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Restaurante extends Estabelecimento {

    @Override
    public void validarRegrasDeNegocio() {
    }

}