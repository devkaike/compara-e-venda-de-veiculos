package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anuncio")
    private Long id;

    private BigDecimal preco;
    private LocalDateTime dataAnuncio;

    @ManyToOne
    @JoinColumn(name = "id_usuario_vendedor")
    private Usuario usuarioVendedor;

    @OneToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

}