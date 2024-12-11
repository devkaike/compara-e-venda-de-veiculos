package com.edu.br.pucgo.compra.e.venda.de.veiculos.modules.Relatorio.controller;

import net.sf.jasperreports.engine.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RelatorioController {

    private final DataSource dataSource;

    public RelatorioController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/relatorio/veiculos")
    public ResponseEntity<byte[]> gerarRelatorioVeiculos(@RequestParam String formato) {
        try {
            String caminhoRelatorio = Paths.get(
                    getClass().getClassLoader().getResource("Relatorio.jasper").toURI()
            ).toString();

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Relatório", "Relatórios");

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    caminhoRelatorio,
                    parametros,
                    dataSource.getConnection()
            );

            System.out.println("Formato recebido: " + formato);

            ByteArrayOutputStream saida = new ByteArrayOutputStream();
            if ("pdf".equalsIgnoreCase(formato)) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, saida);
            }
             else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(("Formato " + formato + " não suportado. Use 'pdf'.").getBytes());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=relatorio." + formato);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(saida.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/relatorio/negociacao")
    public ResponseEntity<byte[]> gerarRelatorioNegociacao(@RequestParam String formato) {
        try {
            String caminhoRelatorio = Paths.get(
                    getClass().getClassLoader().getResource("RelatorioNegociacao.jasper").toURI()
            ).toString();

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Relatório", "Relatórios");

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    caminhoRelatorio,
                    parametros,
                    dataSource.getConnection()
            );

            System.out.println("Formato recebido: " + formato);

            ByteArrayOutputStream saida = new ByteArrayOutputStream();
            if ("pdf".equalsIgnoreCase(formato)) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, saida);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(("Formato " + formato + " não suportado. Use 'pdf'.").getBytes());
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=relatorio." + formato);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(saida.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
