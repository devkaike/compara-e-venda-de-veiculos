package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginRequest;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginResponse;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LoginService {
    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository userRepository;

    public LoginService(JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder, UsuarioRepository userRepository){
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest){
        var user = userRepository.findByEmail(loginRequest.email());

        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("user or password is invalid!");
        }

        var userOptional = user.get();
        var id = userOptional.getId();
        var nome = userOptional.getNome();
        var now = Instant.now();
        var expiresIn = 30L * 24 * 60 * 60 * 1000;;

        String scope = user.get().getRole().getNome();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.get().getEmail())
                .claim("scope",scope)
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn, id, nome);
    }
}