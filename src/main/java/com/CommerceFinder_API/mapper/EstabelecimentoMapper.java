package com.CommerceFinder_API.mapper;

import com.CommerceFinder_API.dto.request.EstabelecimentoRequestDTO;
import com.CommerceFinder_API.dto.response.EstabelecimentoResponseDTO;
import com.CommerceFinder_API.factory.EstabelecimentoFactory;
import com.CommerceFinder_API.model.Estabelecimento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoMapper {

    private final ModelMapper modelMapper;
    private final EstabelecimentoFactory estabelecimentoFactory;


    public EstabelecimentoMapper(ModelMapper modelMapper, EstabelecimentoFactory estabelecimentoFactory) {
        this.modelMapper = modelMapper;
        this.estabelecimentoFactory = estabelecimentoFactory;
    }

    public Estabelecimento toEntity(EstabelecimentoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Estabelecimento entidade = estabelecimentoFactory.criarEstabelecimento(
                dto.getTipo(), dto.getNome(), dto.getLatitude(), dto.getLongitude(), dto.getTelefone()
        );
        modelMapper.map(dto, entidade);
        return entidade;
    }

    public EstabelecimentoResponseDTO toDTO(Estabelecimento entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, EstabelecimentoResponseDTO.class);
    }
}
