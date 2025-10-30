package com.empresatextil.ejercicio4.controller;

import com.empresatextil.ejercicio4.model.Material;
import com.empresatextil.ejercicio4.repository.MaterialRepository;
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
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;
    
    @Autowired
    private SupplierRepository supplierRepository; 

    @GetMapping
    public String listMaterials(Model model) {
        model.addAttribute("materials", materialRepository.findAll());
        return "materials"; 
    }

    @GetMapping("/new")
    public String showMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "material-form"; 
    }

    @PostMapping("/save")
    public String saveMaterial(@Valid Material material, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("suppliers", supplierRepository.findAll());
            return "material-form";
        }
        materialRepository.save(material);
        return "redirect:/materials";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de material inválido:" + id));
        model.addAttribute("material", material);
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "material-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable("id") Long id) {
        materialRepository.deleteById(id);
        return "redirect:/materials";
    }
}