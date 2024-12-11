package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.domain.Anuncio;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.anuncio.repository.AnuncioRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.negociacao.domain.Negociacao;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    public ServiceResponseDTO<?> criarAnuncio(Anuncio anuncio){
        anuncio.setDataAnuncio(LocalDateTime.now());
        anuncioRepository.save(anuncio);
        return new ServiceResponseDTO(200, "Anuncio criado com sucesso!");
    }

    public ServiceResponseDTO<?> listarAnuncio(){
        return new ServiceResponseDTO(200, anuncioRepository.findAll());
    }

    public ServiceResponseDTO<?> listarPeloIdUsuario(Long id){
        List<Anuncio> anuncios = anuncioRepository.findByUsuarioVendedor_Id(id);
        return new ServiceResponseDTO(200, anuncios);
    }

    public ServiceResponseDTO<?> listarPeloModelo(String modelo){
        List<Anuncio> anuncios = anuncioRepository.findByVeiculoModeloContainingIgnoreCase(modelo);
        return new ServiceResponseDTO(200, anuncios);
    }

    public ServiceResponseDTO<?> listarPeloModeloEAno(String modelo, int ano){
        List<Anuncio> anuncios = anuncioRepository.findByVeiculoModeloAndVeiculoAno(modelo, ano);
        return new ServiceResponseDTO(200, anuncios);
    }

    public ServiceResponseDTO<?> excluir(Long id){
        if (!anuncioRepository.existsById(id)) {
            return new ServiceResponseDTO<>(404, "Anuncio não encontrado.");
        }
        anuncioRepository.deleteById(id);
        return new ServiceResponseDTO<>(200, "Anuncio foi excluido.");
    }

    public Anuncio atualizarParcial(Long id, Map<String, Object> updates) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Anúncio não encontrado com ID: " + id));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Anuncio.class, key);
            if (field != null) {
                field.setAccessible(true);
                Object convertedValue = convertValue(value, field.getType());
                ReflectionUtils.setField(field, anuncio, convertedValue);
            }
        });

        return anuncioRepository.save(anuncio);
    }

    private Object convertValue(Object value, Class<?> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType.equals(BigDecimal.class)) {
            return new BigDecimal(value.toString());
        } else if (targetType.equals(LocalDateTime.class)) {
            return LocalDateTime.parse(value.toString());
        } else if (targetType.equals(LocalDate.class)) {
            return LocalDate.parse(value.toString());
        }
        return value;
    }
}
