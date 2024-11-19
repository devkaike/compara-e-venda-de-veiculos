package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestParam("veiculo") String veiculoJson, @RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file provided");
        }

        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();

        try {
            // Converte o JSON recebido para o objeto Veiculo
            ObjectMapper objectMapper = new ObjectMapper();
            Veiculo veiculo = objectMapper.readValue(veiculoJson, Veiculo.class);
            veiculo.setFilename(filename);
            veiculo.setContentType(contentType);

            byte[] content = file.getBytes();
            veiculo.setFileUrl(veiculoService.uploadFile(filename, contentType, content));
            veiculoService.cadastrarVeiculo(veiculo);

            return ResponseEntity.ok().body(veiculo.getFileUrl());
        } catch (IOException | URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        var response = veiculoService.listarVeiculo();
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @PatchMapping(value = "/patch/update/{id}")
    public ResponseEntity<Veiculo> atualizarParcial(
            @PathVariable Long id,
            @RequestParam("veiculo") String updatesJson,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> updates = objectMapper.readValue(updatesJson, Map.class);

            Veiculo veiculoAtualizado = veiculoService.atualizarParcial(id, updates, file);

            return ResponseEntity.ok(veiculoAtualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException | URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deltarVeiculo(@PathVariable Long id){
        var response = veiculoService.excluir(id);
        return ResponseEntity.status(response.status()).body(response.message());
    }
}
