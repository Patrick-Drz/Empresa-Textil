package com.empresatextil.ejercicio4.controller;

import com.empresatextil.ejercicio4.model.Material;
import com.empresatextil.ejercicio4.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) { 
        List<Material> lowStockItems = materialRepository.findLowStockMaterials();
        List<Material> expiredItems = materialRepository.findByExpirationDateBefore(LocalDate.now());

        model.addAttribute("lowStockItems", lowStockItems);
        model.addAttribute("expiredItems", expiredItems);
        
        return "home";
    }
}
