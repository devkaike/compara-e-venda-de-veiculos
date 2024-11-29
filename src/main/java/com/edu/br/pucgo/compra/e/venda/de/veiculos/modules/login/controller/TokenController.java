package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.controller;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginRequest;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginResponse;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Login")
public class TokenController {
    @Autowired
    public LoginService loginService;
    @Operation(summary = "Realiza a autenticação do usuario e retorna o AccessToken",method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario autorizado!"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos!")
    })
    @PostMapping("/auth/user")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        LoginResponse response = loginService.authenticateUser(loginRequest);
        return  ResponseEntity.ok(response);
    }
}