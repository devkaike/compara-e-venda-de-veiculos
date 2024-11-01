package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginRequest;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginResponse;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class TokenController {
    @Autowired
    public LoginService loginService;

    @PostMapping("/auth/user")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        LoginResponse response = loginService.authenticateUser(loginRequest);
        return  ResponseEntity.ok(response);
    }
}