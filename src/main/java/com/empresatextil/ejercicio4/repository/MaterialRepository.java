package com.empresatextil.ejercicio4.repository;

import com.empresatextil.ejercicio4.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    
    @Query("SELECT m FROM Material m WHERE m.stockQuantity <= m.lowStockThreshold")
    List<Material> findLowStockMaterials();

    List<Material> findByExpirationDateBefore(LocalDate today);
}