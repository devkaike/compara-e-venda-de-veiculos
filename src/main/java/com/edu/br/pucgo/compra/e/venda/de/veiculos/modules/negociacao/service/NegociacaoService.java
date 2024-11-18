package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.repository.NegociacaoRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.NegociacaoDTO;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NegociacaoService {

    @Autowired
    private NegociacaoRepository negociacaoRepository;

    public ServiceResponseDTO<?> cadastrarNegociacao(Negociacao negociacao){
        if (negociacao.getComprador().getId() == negociacao.getVendedor().getId()) {
            return new ServiceResponseDTO(400, "Não autorizado!");
        }
        negociacaoRepository.save(negociacao);
        return new ServiceResponseDTO(200, "negociação feita com sucesso!");
    }

    public ServiceResponseDTO<?> listarNegociacao(){
        return new ServiceResponseDTO(200, negociacaoRepository.findAll());
    }

    public ServiceResponseDTO<Negociacao> listarNegociacaoPorId(Long id){
        Optional<Negociacao> negociacao = negociacaoRepository.findById(id);
        return new ServiceResponseDTO(200, negociacao);
    }
}
