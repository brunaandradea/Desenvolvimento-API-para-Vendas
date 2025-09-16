package com.vendabrain.vendas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendabrain.vendas_api.dto.VendedorPerformanceDTO;
import com.vendabrain.vendas_api.model.Venda;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT new com.vendabrain.vendas_api.dto.VendedorPerformanceDTO(" +
           "   v.vendedorNome, " +
           "   COUNT(v.id), " +
           "   SUM(v.valor) / COUNT(DISTINCT v.dataVenda)" +
           ") " +
           "FROM Venda v " +
           "WHERE v.dataVenda BETWEEN :dataInicio AND :dataFim " +
           "GROUP BY v.vendedorId, v.vendedorNome")
    List<VendedorPerformanceDTO> findVendedorPerformanceByPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );
}