package com.atcs.product.service;

import com.atcs.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public void addProduct(Product product);

    public Product findByName(String name);



    public Product findByBrandName(String brand);



    public List<Product> getByParameter(String parameter);

    public Integer servicable(String pincode);

    public List<Product> filterProducts(Double price1, Double price2);


    public List<Product> getAll();
}
