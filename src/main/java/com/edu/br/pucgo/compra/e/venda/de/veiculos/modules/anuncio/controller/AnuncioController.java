package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.service.AnuncioService;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;
    @PostMapping("/criar")
    public ResponseEntity<?> cadastrar(@RequestBody Anuncio anuncio){
        var response = anuncioService.criarAnuncio(anuncio);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        var response = anuncioService.listarAnuncio();
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> listarPorIdUsuario(@PathVariable Long id){
        var response = anuncioService.listarPeloIdUsuario(id);
        return ResponseEntity.status(response.status()).body(response.message());
    }
}

