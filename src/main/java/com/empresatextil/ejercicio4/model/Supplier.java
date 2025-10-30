package com.empresatextil.ejercicio4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;
    private String contactPerson;
    private String phone;

    @OneToMany(mappedBy = "supplier")
    private Set<Material> materials;
    
    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> purchaseOrders;
}