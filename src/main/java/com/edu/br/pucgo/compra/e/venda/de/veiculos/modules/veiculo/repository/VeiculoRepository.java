package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByUsuario_Id(Long id);

    List<Veiculo> findByModelo(String modelo);

    List<Veiculo> findByModeloAndAno(String modelo, int ano);
}
