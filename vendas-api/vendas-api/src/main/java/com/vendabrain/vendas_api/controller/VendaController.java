package com.vendabrain.vendas_api.controller;

import com.vendabrain.vendas_api.dto.VendaRequestDTO;
import com.vendabrain.vendas_api.dto.VendedorPerformanceDTO;
import com.vendabrain.vendas_api.model.Venda;
import com.vendabrain.vendas_api.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController 
@RequestMapping("/api") 
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("/vendas")
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaRequestDTO vendaRequestDTO) {
        Venda novaVenda = vendaService.criarVenda(vendaRequestDTO);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED); 
    }

    @GetMapping("/vendedores")
    public ResponseEntity<List<VendedorPerformanceDTO>> listarPerformance(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        
        List<VendedorPerformanceDTO> performance = vendaService.listarPerformanceVendedores(dataInicio, dataFim);
        return ResponseEntity.ok(performance);
    }
}
