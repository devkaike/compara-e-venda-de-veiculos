package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.login.dto.LoginRequest;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.roles.domain.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    private String nome;

    private String telefone;

    private String cpf;

    @Column(name = "conta_criada")
    private LocalDateTime contaCriada = LocalDateTime.now();

    private String estado;

    private String cidade;

    private String email;

    private String Senha;
    @ManyToOne
    @JoinColumn
    private Role role;

    @OneToMany(mappedBy = "usuarioVendedor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Anuncio> anuncios;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<Negociacao> negociacaos;

    public boolean isLoginCorrect(LoginRequest loginRequest, BCryptPasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginRequest.senha(), this.Senha);
    }
}
