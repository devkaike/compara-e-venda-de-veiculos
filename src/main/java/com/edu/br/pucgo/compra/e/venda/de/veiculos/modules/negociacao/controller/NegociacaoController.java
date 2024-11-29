package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.repository.NegociacaoRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.service.NegociacaoService;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.NegociacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/negociacao")
public class NegociacaoController {

    @Autowired
    private NegociacaoService negociacaoService;

    @Autowired
    private NegociacaoRepository negociacaoRepository;

    @PostMapping(value = "/do")
    public ResponseEntity<?> cadastrar(@RequestBody Negociacao negociacao){
        var response = negociacaoService.cadastrarNegociacao(negociacao);
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        var response = negociacaoService.listarNegociacao();
        return ResponseEntity.status(response.status()).body(response.message());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<NegociacaoDTO> getNegociacao(@PathVariable Long id) {
        Optional<Negociacao> negociacao = negociacaoRepository.findById(id);
        NegociacaoDTO dto = new NegociacaoDTO(negociacao.get().getId(), negociacao.get().getComprador(), negociacao.get().getVendedor(), negociacao.get().getAnuncio());
        return ResponseEntity.ok(dto);
    }
}
