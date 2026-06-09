package com.CommerceFinder_API.controller;

import com.CommerceFinder_API.dto.request.EstabelecimentoRequestDTO;
import com.CommerceFinder_API.dto.response.EstabelecimentoResponseDTO;
import com.CommerceFinder_API.model.Estabelecimento;
import com.CommerceFinder_API.service.EstabelecimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estabelecimentos")
@RequiredArgsConstructor
@Slf4j
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @GetMapping("/buscar")
    public ResponseEntity<List<Estabelecimento>> buscarEOrdenar(
            @RequestParam(defaultValue = "distancia") String ordenarPor,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {

        List<Estabelecimento> resultado = estabelecimentoService.buscarEEstruturar(ordenarPor, latitude, longitude);

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponseDTO> salvar(@Valid @RequestBody EstabelecimentoRequestDTO estabelecimentoRequestDTO) {
        log.info("Recebida requisição para criar novo estabelecimento: {}", estabelecimentoRequestDTO.getNome());
        try {
            EstabelecimentoResponseDTO estabelecimentoSalvo = estabelecimentoService.salvar(estabelecimentoRequestDTO);
            log.info("Estabelecimento criado com sucesso. ID: {}", estabelecimentoSalvo.getId());

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(estabelecimentoSalvo.getId())
                    .toUri();
            log.debug("URI de localização do novo estabelecimento: {}", location);

            return ResponseEntity.created(location).body(estabelecimentoSalvo);
        } catch (Exception e) {
            log.error("Erro ao criar estabelecimento: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping
    public List<EstabelecimentoResponseDTO> listar() {
        log.info("Listando todos os estabelecimentos");
        List<EstabelecimentoResponseDTO> estabelecimentos = estabelecimentoService.listar();
        log.debug("Total de estabelecimentos encontrados: {}", estabelecimentos.size());
        return estabelecimentos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstabelecimentoResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody EstabelecimentoRequestDTO estabelecimentoRequestDTO) {
        log.info("Atualizando estabelecimento com ID {}: {}", id, estabelecimentoRequestDTO);
        try {
            EstabelecimentoResponseDTO estabelecimentoAtualizada = estabelecimentoService.atualizar(id, estabelecimentoRequestDTO);
            log.debug("Estabelecimento ID {} atualizado com sucesso", id);
            return ResponseEntity.ok(estabelecimentoAtualizada);
        } catch (Exception e) {
            log.error("Erro ao atualizar estabelecimento ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        log.info("Excluindo estabelecimento com ID: {}", id);
        try {
            estabelecimentoService.excluir(id);
            log.debug("Estabelecimento com ID {} excluído com sucesso", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir estabelecimento com ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar-ordenados")
    public ResponseEntity<List<Estabelecimento>> listar(
            @RequestParam(value = "ordenarPor", defaultValue = "distancia") String ordenarPor,
            @RequestParam(value = "lat", required = false) Double latitude,
            @RequestParam(value = "lng", required = false) Double longitude) {

        List<Estabelecimento> resultado = estabelecimentoService.listarEstabelecimentosOrdenados(ordenarPor, latitude, longitude);
        return ResponseEntity.ok(resultado);
    }

}
