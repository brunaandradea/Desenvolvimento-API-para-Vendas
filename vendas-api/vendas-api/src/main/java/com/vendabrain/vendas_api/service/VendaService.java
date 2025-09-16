package com.vendabrain.vendas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendabrain.vendas_api.dto.VendaRequestDTO;
import com.vendabrain.vendas_api.dto.VendedorPerformanceDTO;
import com.vendabrain.vendas_api.model.Venda;
import com.vendabrain.vendas_api.repository.VendaRepository;

import java.time.LocalDate;
import java.util.List;

@Service 
public class VendaService {

    @Autowired 
    private VendaRepository vendaRepository;

    /**
     * Cria uma nova venda no sistema.
     * @param requestDTO Os dados da venda a ser criada.
     * @return A entidade Venda que foi salva no banco.
     */
    public Venda criarVenda(VendaRequestDTO requestDTO) {
        Venda novaVenda = new Venda();
        novaVenda.setVendedorId(requestDTO.getVendedorId());
        novaVenda.setVendedorNome(requestDTO.getVendedorNome());
        novaVenda.setValor(requestDTO.getValor());
        novaVenda.setDataVenda(LocalDate.now());

        return vendaRepository.save(novaVenda);
    }

    /**
     * Busca a performance de todos os vendedores dentro de um período específico.
     * @param dataInicio A data de início do período.
     * @param dataFim A data de fim do período.
     * @return Uma lista com os dados de performance de cada vendedor.
     */
    public List<VendedorPerformanceDTO> listarPerformanceVendedores(LocalDate dataInicio, LocalDate dataFim) {
        return vendaRepository.findVendedorPerformanceByPeriodo(dataInicio, dataFim);
    }
}