package com.example.order_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String port;

    @PostMapping("/{skuCode}")
    @CircuitBreaker(name = "inventory", fallbackMethod = "stokHatasiBPlani")
    public String isInStock(@PathVariable String skuCode){
        boolean inStock = inventoryClient.isInStock(skuCode);
        if (inStock){
            return "Sipariş başarıyla verildi. (İşlemi Yapan Port: " + port + ")";
        }
        else {
            return "Ürün stokta bulunamadı.(İşlemi Yapan Port: " + port + ")";
        }
    }

    public String stokHatasiBPlani(@PathVariable String skuCode,Throwable e){
        return "Şu an yoğunluk var.İşleminiz sıraya alınmıştır. İşlemi yapan port: " + port;
    }
}
