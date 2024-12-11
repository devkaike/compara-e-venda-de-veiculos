package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@Data
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String preco;

    @Column(nullable = false)
    private String cor;

    @Column(name = "tipo_combustivel", nullable = false)
    private String tipoCombustivel;

    @Column(nullable = false)
    private Integer quilometragem;

    @Column(name = "tipo_veiculo", nullable = false)
    private String tipoVeiculo;

    @Column(nullable = false)
    private String cambio;

    @Column(nullable = false)
    private Integer potencia;

    @Column(name = "quantidade_portas", nullable = false)
    private Integer quantidadePortas;

    @Column(name = "caracteristicas_especificas")
    private String caracteristicasEspecificas;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String filename;
    private String fileUrl;
    private String contentType;

}
