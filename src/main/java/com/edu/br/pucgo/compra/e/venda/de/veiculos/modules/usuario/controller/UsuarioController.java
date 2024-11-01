package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/criar")
    public ResponseEntity<?> criar(@Validated @RequestBody Usuario user){
        var response = usuarioService.adicionarUsuario(user);
        return ResponseEntity.status(response.status()).body(response.message());
    }
}
