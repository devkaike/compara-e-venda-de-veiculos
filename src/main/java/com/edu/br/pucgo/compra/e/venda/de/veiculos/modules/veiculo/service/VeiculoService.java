package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.service;

import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.domain.Veiculo;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.veiculo.repository.VeiculoRepository;
import com.edu.br.pucgo.compra.e.venda.de.veiculos.util.DTOs.ServiceResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    public String uploadFile(String filename, String contentType, byte[] content) throws URISyntaxException {

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(putOb, RequestBody.fromBytes(content));

            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, filename);
        } catch (S3Exception e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    public ServiceResponseDTO<?> cadastrarVeiculo(Veiculo veiculo){
        veiculoRepository.save(veiculo);
        return new ServiceResponseDTO(200, "Veiculo cadastrado com sucesso!");
    }

    public ServiceResponseDTO<?> listarVeiculo(){
        return new ServiceResponseDTO(200, veiculoRepository.findAll());
    }

    public Veiculo atualizarParcial(Long id, Map<String, Object> updates, MultipartFile file) throws IOException, URISyntaxException {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
        if (!optionalVeiculo.isPresent()) {
            throw new NoSuchElementException("Veiculo não encontrado");
        }

        Veiculo veiculo = optionalVeiculo.get();

        // Atualiza apenas os campos enviados na requisição
        updates.forEach((chave, valor) -> {
            Field campo = ReflectionUtils.findField(Veiculo.class, chave);
            if (campo != null) {
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, veiculo, valor);
            }
        });

        // Se um arquivo for enviado, faz o upload e atualiza a URL
        if (file != null && !file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] content = file.getBytes();
            String fileUrl = uploadFile(filename, contentType, content); // Chama o método para fazer o upload do arquivo
            veiculo.setFileUrl(fileUrl); // Atualiza a URL do arquivo
        }

        // Salva as alterações no banco de dados
        return veiculoRepository.save(veiculo);
    }

    public ServiceResponseDTO<?> excluir(Long id){
        veiculoRepository.deleteById(id);
        return new ServiceResponseDTO(200, "Veiculo foi excluido.");
    }

    public  ServiceResponseDTO<?> listarPorId(Long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return new ServiceResponseDTO(200, veiculo);
    }

    public ServiceResponseDTO<?> listarPeloIdUsuario(Long id){
        List<Veiculo> veiculos = veiculoRepository.findByUsuario_Id(id);
        return new ServiceResponseDTO(200, veiculos);
    }

    public ServiceResponseDTO<?> listarPeloModelo(String modelo){
        List<Veiculo> veiculos = veiculoRepository.findByModelo(modelo);
        return new ServiceResponseDTO(200, veiculos);
    }

    public ServiceResponseDTO<?> listarPeloModeloEAno(String modelo, int ano){
        List<Veiculo> veiculos = veiculoRepository.findByModeloAndAno(modelo, ano);
        return new ServiceResponseDTO(200, veiculos);
    }
}
