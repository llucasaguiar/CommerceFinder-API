package com.CommerceFinder_API.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("BAR")
@NoArgsConstructor(force = true)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Bar extends Estabelecimento {



    @Override
    public void validarRegrasDeNegocio() {

        if (this.getNome().toLowerCase().contains("infantil")) {
            throw new IllegalArgumentException("Bares não podem conter 'infantil' no nome.");
        }
    }

}