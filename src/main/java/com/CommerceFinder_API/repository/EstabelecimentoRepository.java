package com.CommerceFinder_API.repository;

import com.CommerceFinder_API.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, UUID> {
}
