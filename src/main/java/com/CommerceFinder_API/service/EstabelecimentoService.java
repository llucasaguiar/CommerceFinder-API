package com.CommerceFinder_API.service;

import com.CommerceFinder_API.dto.request.EstabelecimentoRequestDTO;
import com.CommerceFinder_API.dto.response.EstabelecimentoResponseDTO;
import com.CommerceFinder_API.mapper.EstabelecimentoMapper;
import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.repository.EstabelecimentoRepository;
import com.CommerceFinder_API.strategy.ContextoOrdenacao;
import com.CommerceFinder_API.strategy.OrdenacaoStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ContextoOrdenacao contextoOrdenacao;
    private final EstabelecimentoMapper estabelecimentoMapper;

    @Transactional
    public EstabelecimentoResponseDTO salvar(EstabelecimentoRequestDTO estabelecimentoRequestDTO) {
        log.info("Salvando novo estabelecimento: {}", estabelecimentoRequestDTO.getNome());
        try {
            Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(estabelecimentoRequestDTO);
            Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimento);
            log.info("Estabelecimento salvo com sucesso. ID: {}", estabelecimentoSalvo.getId());
            return estabelecimentoMapper.toDTO(estabelecimentoSalvo);
        } catch (Exception e) {
            log.error("Falha ao salvar estabelecimento '{}': {}", estabelecimentoRequestDTO.getNome(), e.getMessage(), e);
            throw e;
        }
    }

    public List<EstabelecimentoResponseDTO> listar() {
        log.info("Buscando todos os estabelecimentos cadastrados");
        try {
            List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
            List<EstabelecimentoResponseDTO> estabelecimentosDTO = estabelecimentos.stream()
                    .map(estabelecimentoMapper::toDTO)
                    .collect(Collectors.toList());
            log.debug("Total de estabelecimentos encontrados: {}", estabelecimentosDTO.size());
            return estabelecimentosDTO;
        } catch (Exception e) {
            log.error("Falha ao buscar estabelecimentos: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public EstabelecimentoResponseDTO atualizar(UUID id, EstabelecimentoRequestDTO estabelecimentoRequestDTO) {
        log.info("Atualizando estabelecimento ID: {}", id);
        Estabelecimento estabelecimentoAtualizado = estabelecimentoRepository.findById(id)
                .map(estabelecimentoExistente -> {
                    log.debug("Dados atuais do estabelecimento: {}", estabelecimentoExistente);
                    log.debug("Novos dados recebidos: {}", estabelecimentoRequestDTO);

                    // Usando a inteligência da Fábrica mapeada dentro do toEntity
                    Estabelecimento estabelecimentoParaAtualizar = estabelecimentoMapper.toEntity(estabelecimentoRequestDTO);
                    estabelecimentoParaAtualizar.setId(id); // Garante a substituição no ID correto

                    Estabelecimento estabelecimentoSalvo = estabelecimentoRepository.save(estabelecimentoParaAtualizar);
                    log.info("Estabelecimento ID: {} atualizado com sucesso.", id);
                    return estabelecimentoSalvo;
                })
                .orElseThrow(() -> {
                    String mensagem = String.format("Falha ao atualizar: estabelecimento não encontrado com o ID: %d", id);
                    log.warn(mensagem);
                    return new RuntimeException(mensagem);
                });
        return estabelecimentoMapper.toDTO(estabelecimentoAtualizado);
    }

    @Transactional
    public void excluir(UUID id) {
        log.info("Excluindo estabelecimento ID: {}", id);
        if (!estabelecimentoRepository.existsById(id)) {
            String mensagem = String.format("Falha ao excluir: estabelecimento não encontrado com o ID: %d", id);
            log.warn(mensagem);
            throw new RuntimeException(mensagem);
        }
        try {
            estabelecimentoRepository.deleteById(id);
            log.info("Estabelecimento ID: {} excluído com sucesso", id);
        } catch (Exception e) {
            log.error("Erro ao excluir estabelecimento ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    public List<EstabelecimentoResponseDTO> listarComOrdenacao(String ordenarPor, Double latitude, Double longitude) {
        log.info("Buscando estabelecimentos ordenados por: {}", ordenarPor);
        try {
            List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();

            OrdenacaoStrategy estrategia = contextoOrdenacao.obterEstrategia(ordenarPor);

            List<Estabelecimento> estabelecimentosOrdenados = estrategia.ordenar(estabelecimentos, latitude, longitude);

            return estabelecimentosOrdenados.stream()
                    .map(estabelecimentoMapper::toDTO)
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            log.warn("Erro de validação na ordenação: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Falha ao listar estabelecimentos ordenados: {}", e.getMessage(), e);
            throw e;
        }
    }


}
