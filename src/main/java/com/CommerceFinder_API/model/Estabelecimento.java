package com.CommerceFinder_API.model;

import com.CommerceFinder_API.enums.TipoEstabelecimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estabelecimentos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_estabelecimento", discriminatorType = DiscriminatorType.STRING)
public abstract class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 50)
    private TipoEstabelecimento tipo; // "Bar", "Café", "Restaurante"

    private double precoMin;
    private double precoMax;
    private double notaAvaliacao;
    private double latitude;
    private double longitude;
    private String telefone;
    private boolean estaAberto;

    @Transient
    private double distanciaEmKm;

    // Método auxiliar que simula o cálculo de distância (Haversine simplificado)
    public double calcularDistanciaAte(double latitudeUsuario, double longitudeUsuario) {
        double deltaLat = this.latitude - latitudeUsuario;
        double deltaLng = this.longitude - longitudeUsuario;
        return Math.sqrt(deltaLat * deltaLat + deltaLng * deltaLng);
    }

    public abstract void validarRegrasDeNegocio();

}
