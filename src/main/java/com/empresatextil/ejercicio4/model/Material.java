package com.empresatextil.ejercicio4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre es obligatorio")
    private String name;
    
    private String description;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stockQuantity;

    @Min(value = 1, message = "El umbral debe ser al menos 1")
    private int lowStockThreshold = 10;

    @Column(nullable = true) 
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}