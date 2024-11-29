package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<?> listarPorModelo(@PathVariable String modelo){
        var response = anuncioService.listarPeloModelo(modelo);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/modelo/{modelo}/ano/{ano}")
    public ResponseEntity<?> listarPorModeloEAno(@PathVariable String modelo, @PathVariable int ano){
        var response = anuncioService.listarPeloModeloEAno(modelo, ano);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deltarAnuncio(@PathVariable Long id){
        var response = anuncioService.excluir(id);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<Anuncio> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        Anuncio anuncioAtualizado = anuncioService.atualizarParcial(id, updates);
        return ResponseEntity.ok(anuncioAtualizado);
    }
}

