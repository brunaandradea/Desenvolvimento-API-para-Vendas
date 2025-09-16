package com.vendabrain.vendas_api.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorPerformanceDTO {
    private String nomeVendedor;
    private Long totalVendas;
    private BigDecimal mediaVendasDiaria;
}
