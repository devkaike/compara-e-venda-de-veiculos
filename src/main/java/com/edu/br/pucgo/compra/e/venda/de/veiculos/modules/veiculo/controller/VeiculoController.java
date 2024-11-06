package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.service.VeiculoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Veiculo veiculo){
        var response = veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        var response = veiculoService.listarVeiculo();
        return ResponseEntity.status(response.status()).body(response.message());
    }
}
