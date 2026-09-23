package com.example.order_service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping("/{skuCode}")
    public String isInStock(@PathVariable String skuCode){
        RestTemplate restTemplate = new RestTemplate();
        Boolean inStock = restTemplate.getForObject("http://localhost:8082/api/inventory/"+skuCode,Boolean.class);
        if (inStock){
            return "Sipariş başarıyla oluşturuldu.";
        }
        else {
            return "Ürün stokta bulunamadı.";
        }
    }
}
