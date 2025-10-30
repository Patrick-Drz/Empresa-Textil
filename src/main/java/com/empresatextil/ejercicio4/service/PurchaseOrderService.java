package com.empresatextil.ejercicio4.service;

import com.empresatextil.ejercicio4.model.Material;
import com.empresatextil.ejercicio4.model.OrderDetail;
import com.empresatextil.ejercicio4.model.OrderStatus;
import com.empresatextil.ejercicio4.model.PurchaseOrder;
import com.empresatextil.ejercicio4.repository.MaterialRepository;
import com.empresatextil.ejercicio4.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    public void receiveOrder(Long orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("La orden ya fue procesada o cancelada");
        }

        for (OrderDetail detail : order.getOrderDetails()) {
            Material material = detail.getMaterial();
            int newStock = material.getStockQuantity() + detail.getQuantity();
            material.setStockQuantity(newStock);
            materialRepository.save(material);
        }

        order.setStatus(OrderStatus.RECEIVED);
        purchaseOrderRepository.save(order);
    }
}