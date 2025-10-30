package com.empresatextil.ejercicio4.controller;

import com.empresatextil.ejercicio4.model.OrderDetail;
import com.empresatextil.ejercicio4.model.PurchaseOrder;
import com.empresatextil.ejercicio4.repository.MaterialRepository;
import com.empresatextil.ejercicio4.repository.OrderDetailRepository;
import com.empresatextil.ejercicio4.repository.PurchaseOrderRepository;
import com.empresatextil.ejercicio4.repository.SupplierRepository;
import com.empresatextil.ejercicio4.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class PurchaseOrderController {

    @Autowired private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired private SupplierRepository supplierRepository;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private OrderDetailRepository orderDetailRepository;
    @Autowired private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", purchaseOrderRepository.findAll());
        return "purchase-order-list";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new PurchaseOrder());
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "purchase-order-form";
    }

    @PostMapping("/save")
    public String saveOrder(PurchaseOrder order) {
        PurchaseOrder newOrder = purchaseOrderRepository.save(order);
        return "redirect:/orders/details/" + newOrder.getId();
    }

    @GetMapping("/details/{id}")
    public String showOrderDetails(@PathVariable("id") Long id, Model model) {
        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        
        model.addAttribute("order", order);
        model.addAttribute("newDetail", new OrderDetail());
        model.addAttribute("materials", materialRepository.findAll());
        return "purchase-order-details";
    }

    @PostMapping("/details/{orderId}/add")
    public String addDetailToOrder(@PathVariable("orderId") Long orderId, OrderDetail newDetail) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId).get();
        
        newDetail.setPurchaseOrder(order);
        orderDetailRepository.save(newDetail);
        
        return "redirect:/orders/details/" + orderId;
    }

    @PostMapping("/receive/{orderId}")
    public String receiveOrder(@PathVariable("orderId") Long orderId) {
        purchaseOrderService.receiveOrder(orderId);
        return "redirect:/materials"; 
    }
    
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id).get();
        orderDetailRepository.deleteAll(order.getOrderDetails());
        purchaseOrderRepository.delete(order);
        return "redirect:/orders";
    }
}
