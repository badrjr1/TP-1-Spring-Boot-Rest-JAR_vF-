package com.example.rest.service;
import com.example.rest.model.Product;

import java.util.List;

public interface IProdcutService {
    Product getById(Long id);
    List<Product> getAll();
    void create(Product product);
    void update(Long id, Product product);
    void delete(Long id);
}
