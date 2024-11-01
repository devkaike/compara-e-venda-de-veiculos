package com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs;

public record ServiceResponseDTO<Type>(int status, Type message) {
}
