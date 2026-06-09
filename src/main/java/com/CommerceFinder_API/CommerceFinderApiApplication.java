package com.CommerceFinder_API;

import com.CommerceFinder_API.dto.request.EstabelecimentoRequestDTO;
import com.CommerceFinder_API.dto.response.EstabelecimentoResponseDTO;
import com.CommerceFinder_API.enums.TipoEstabelecimento;
import com.CommerceFinder_API.service.EstabelecimentoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CommerceFinderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceFinderApiApplication.class, args);
	}

    @Bean
    public CommandLineRunner executarCargaDeDados(EstabelecimentoService servico) {
        return args -> {
            System.out.println("\n=== INICIANDO CARGA AUTOMÁTICA DE TESTES (H2) ===");

            // Bares
            EstabelecimentoRequestDTO bar1 = new EstabelecimentoRequestDTO();
            bar1.setNome("Bar do Zé - Centro");
            bar1.setTipo(TipoEstabelecimento.valueOf("BAR"));
            bar1.setNotaAvaliacao(5.0);
            bar1.setTelefone("00000000000");
            bar1.setLatitude(-7.1196);
            bar1.setLongitude(-34.8450);

            EstabelecimentoRequestDTO bar2 = new EstabelecimentoRequestDTO();
            bar2.setNome("Pub Rock & Beer");
            bar2.setTipo(TipoEstabelecimento.valueOf("BAR"));
            bar2.setNotaAvaliacao(4.5);
            bar2.setTelefone("11111111111");
            bar2.setLatitude(-7.1250);
            bar2.setLongitude(-34.8510);

            // Cafés
            EstabelecimentoRequestDTO cafe1 = new EstabelecimentoRequestDTO();
            cafe1.setNome("Café Central Universitário");
            cafe1.setTipo(TipoEstabelecimento.valueOf("CAFE"));
            cafe1.setNotaAvaliacao(4.6);
            cafe1.setTelefone("00000000000");
            cafe1.setLatitude(-7.1210);
            cafe1.setLongitude(-34.8465);

            EstabelecimentoRequestDTO cafe2 = new EstabelecimentoRequestDTO();
            cafe2.setNome("Espresso Gourmet");
            cafe2.setTipo(TipoEstabelecimento.valueOf("CAFE"));
            cafe2.setNotaAvaliacao(4.8);
            cafe2.setTelefone("11111111111");
            cafe2.setLatitude(-7.1110);
            cafe2.setLongitude(-34.8210);

            // Restaurantes
            EstabelecimentoRequestDTO rest1 = new EstabelecimentoRequestDTO();
            rest1.setNome("Restaurante Sabor Local");
            rest1.setTipo(TipoEstabelecimento.valueOf("RESTAURANTE"));
            rest1.setTelefone("00000000000");
            rest1.setNotaAvaliacao(4.9);
            rest1.setTelefone("00000000000");
            rest1.setLatitude(-7.1150);
            rest1.setLongitude(-34.8230);

            EstabelecimentoRequestDTO rest2 = new EstabelecimentoRequestDTO();
            rest2.setNome("Bistrô Parisiense");
            rest2.setTipo(TipoEstabelecimento.valueOf("RESTAURANTE"));
            rest2.setNotaAvaliacao(5.0);
            rest2.setTelefone("11111111111");
            rest2.setLatitude(-7.1320);
            rest2.setLongitude(-34.8620);

            servico.salvar(bar1);
            servico.salvar(bar2);
            servico.salvar(cafe1);
            servico.salvar(cafe2);
            servico.salvar(rest1);
            servico.salvar(rest2);

            System.out.println("=== 6 ESTABELECIMENTOS CADASTRADOS COM SUCESSO ===\n");

            System.out.println("\n=== EXIBINDO CADASTROS SALVOS SEPARADOS POR TIPO ===");

            // 1. Busca todos os estabelecimentos cadastrados no H2
            List<EstabelecimentoResponseDTO> todosOsEstabelecimentos = servico.listar();

            // 2. Filtrar e exibir apenas os BARES
            System.out.println("\n[BARES CADASTRADOS]:");
            todosOsEstabelecimentos.stream()
                    .filter(est -> "BAR".equalsIgnoreCase(String.valueOf(est.getTipo())))
                    .forEach(bar -> System.out.println(" -> Nome: " + bar.getNome() + " Nota: " + bar.getNotaAvaliacao() + " Telefone: " + bar.getTelefone() + " (Lat: " + bar.getLatitude() + " / Lng: " + bar.getLongitude() + ")"));

            // 3. Filtrar e exibir apenas os CAFÉS
            System.out.println("\n[CAFÉS CADASTRADOS]:");
            todosOsEstabelecimentos.stream()
                    .filter(est -> "CAFE".equalsIgnoreCase(String.valueOf(est.getTipo())))
                    .forEach(cafe -> System.out.println(" -> Nome: " + cafe.getNome() + " Nota: " + cafe.getNotaAvaliacao() + " Telefone: " + cafe.getTelefone() + " (Lat: " + cafe.getLatitude() + " / Lng: " + cafe.getLongitude() + ")"));

            // 4. Filtrar e exibir apenas os RESTAURANTES
            System.out.println("\n[RESTAURANTES CADASTRADOS]:");
            todosOsEstabelecimentos.stream()
                    .filter(est -> "RESTAURANTE".equalsIgnoreCase(String.valueOf(est.getTipo())))
                    .forEach(rest -> System.out.println(" -> Nome: " + rest.getNome() + " Nota: " + rest.getNotaAvaliacao() + " Telefone: " + rest.getTelefone() + " (Lat: " + rest.getLatitude() + " / Lng: " + rest.getLongitude() + ")"));

            System.out.println("\n====================================================");

        };
    }

}

