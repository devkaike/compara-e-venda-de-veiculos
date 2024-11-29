package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.repository;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByUsuarioVendedor_Id(@Param("id_anuncio") Long id);
}
