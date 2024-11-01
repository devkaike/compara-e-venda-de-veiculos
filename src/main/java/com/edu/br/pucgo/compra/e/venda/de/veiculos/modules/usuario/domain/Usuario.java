package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginRequest;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.domain.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "usuario")
@Data
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    private String email;

    private String Senha;
    @ManyToOne
    @JoinColumn
    private Role role;

    public boolean isLoginCorrect(LoginRequest loginRequest, BCryptPasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginRequest.senha(), this.Senha);
    }
}
