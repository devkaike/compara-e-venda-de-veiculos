package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.repository.VeiculoRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public ServiceResponseDTO<?> cadastrarVeiculo(Veiculo veiculo){
        veiculoRepository.save(veiculo);
        return new ServiceResponseDTO(200, "Veiculo cadastrado com sucesso!");
    }

    public ServiceResponseDTO<?> listarVeiculo(){
        return new ServiceResponseDTO(200, veiculoRepository.findAll());
    }
}
