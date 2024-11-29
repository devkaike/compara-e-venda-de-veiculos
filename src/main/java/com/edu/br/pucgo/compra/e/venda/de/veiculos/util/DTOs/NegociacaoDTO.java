package com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.usuario.domain.Usuario;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegociacaoDTO {
    private Long id;
    private Usuario comprador;
    private Usuario vendedor;
    private Anuncio anuncio;
}
