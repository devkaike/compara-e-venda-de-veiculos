package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Negociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_negociacao")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_comprador")
    @JsonIgnoreProperties({"compras", "senha"})
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "id_usuario_vendedor")
    @JsonIgnoreProperties({"compras", "senha"})
    private Usuario vendedor;

    @OneToOne
    @JoinColumn(name = "id_anuncio")
    private Anuncio anuncio;
}
