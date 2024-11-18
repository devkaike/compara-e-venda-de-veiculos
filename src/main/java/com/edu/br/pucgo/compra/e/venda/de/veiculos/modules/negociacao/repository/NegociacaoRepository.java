package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegociacaoRepository extends JpaRepository<Negociacao, Long> {
}
