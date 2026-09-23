package com.example.order_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory", url = "http://localhost:8082")
public interface InventoryClient {
    @GetMapping("/api/inventory/{skuCode}")
    public Boolean isInStock(@PathVariable String skuCode);

}
