package com.edu.br.pucgo.compra.e.venda.de.veiculos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Venda de veiculos", version = "1", description = "API desenvolvida para projeto final da materia Cliente/Servidor"))
public class CompraEVendaDeVeiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraEVendaDeVeiculosApplication.class, args);
	}

}
