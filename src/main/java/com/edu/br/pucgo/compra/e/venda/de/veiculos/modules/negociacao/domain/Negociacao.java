package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@Data
public class Negociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_negociacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "id_anuncio")
    private Anuncio anuncio;
}
