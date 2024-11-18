package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.repository.AnuncioRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    public ServiceResponseDTO<?> criarAnuncio(Anuncio anuncio){
        anuncioRepository.save(anuncio);
        return new ServiceResponseDTO(200, "Anuncio criado com sucesso!");
    }

    public ServiceResponseDTO<?> listarAnuncio(){
        return new ServiceResponseDTO(200, anuncioRepository.findAll());
    }
}
