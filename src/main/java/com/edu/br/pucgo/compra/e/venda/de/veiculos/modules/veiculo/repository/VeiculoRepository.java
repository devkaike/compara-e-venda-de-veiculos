package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
