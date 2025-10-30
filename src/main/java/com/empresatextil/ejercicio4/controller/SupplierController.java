package com.empresatextil.ejercicio4.controller;

import com.empresatextil.ejercicio4.model.Supplier;
import com.empresatextil.ejercicio4.repository.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier-list"; 
    }

    @GetMapping("/new")
    public String showSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier-form"; 
    }

    @PostMapping("/save")
    public String saveSupplier(@Valid Supplier supplier, BindingResult result) {
        if (result.hasErrors()) {
            return "supplier-form";
        }
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de proveedor inválido:" + id));
        model.addAttribute("supplier", supplier);
        return "supplier-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierRepository.deleteById(id);
        return "redirect:/suppliers";
    }
}
