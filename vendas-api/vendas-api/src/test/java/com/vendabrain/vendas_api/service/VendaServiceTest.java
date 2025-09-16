package com.vendabrain.vendas_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vendabrain.vendas_api.dto.VendaRequestDTO;
import com.vendabrain.vendas_api.dto.VendedorPerformanceDTO;
import com.vendabrain.vendas_api.model.Venda;
import com.vendabrain.vendas_api.repository.VendaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @InjectMocks
    private VendaService vendaService;

    @Test
    void deveCriarVendaComSucesso() {
        
        VendaRequestDTO request = new VendaRequestDTO();
        request.setVendedorId(1L);
        request.setVendedorNome("João");
        request.setValor(new BigDecimal("150.00"));

        Venda vendaSalva = new Venda();
        vendaSalva.setId(1L);
        vendaSalva.setDataVenda(LocalDate.now());
        vendaSalva.setVendedorId(request.getVendedorId());
        vendaSalva.setVendedorNome(request.getVendedorNome());
        vendaSalva.setValor(request.getValor());

        when(vendaRepository.save(any(Venda.class))).thenReturn(vendaSalva);

        Venda resultado = vendaService.criarVenda(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João", resultado.getVendedorNome());
        assertEquals(LocalDate.now(), resultado.getDataVenda());
    }

    @Test
    void deveListarPerformanceDeVendedores() {

        LocalDate inicio = LocalDate.of(2025, 9, 1);
        LocalDate fim = LocalDate.of(2025, 9, 15);
        
        VendedorPerformanceDTO vendedor1 = new VendedorPerformanceDTO("João", 5L, new BigDecimal("250.50"));
        VendedorPerformanceDTO vendedor2 = new VendedorPerformanceDTO("Maria", 3L, new BigDecimal("400.00"));
        List<VendedorPerformanceDTO> mockLista = Arrays.asList(vendedor1, vendedor2);

        when(vendaRepository.findVendedorPerformanceByPeriodo(inicio, fim)).thenReturn(mockLista);

        List<VendedorPerformanceDTO> resultado = vendaService.listarPerformanceVendedores(inicio, fim);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNomeVendedor());
        assertEquals(3L, resultado.get(1).getTotalVendas());
    }
}