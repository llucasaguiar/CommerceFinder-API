package com.CommerceFinder_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CAFE")
@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Cafe extends Estabelecimento {

    @Override
    public void validarRegrasDeNegocio() {
    }

}
