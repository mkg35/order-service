package com.example.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final InventoryClient inventoryClient;

    @PostMapping("/{skuCode}")
    public String isInStock(@PathVariable String skuCode){
        boolean inStock = inventoryClient.isInStock(skuCode);
        if (inStock){
            return "Sipariş başarıyla verildi";
        }
        else {
            return "Ürün stokta bulunamadı.";
        }
    }
}
