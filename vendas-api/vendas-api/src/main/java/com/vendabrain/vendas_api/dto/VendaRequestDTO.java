package com.vendabrain.vendas_api.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class VendaRequestDTO {
    private BigDecimal valor;
    private Long vendedorId;
    private String vendedorNome;
}
