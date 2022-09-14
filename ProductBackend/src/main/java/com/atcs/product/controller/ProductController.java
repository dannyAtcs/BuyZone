package com.atcs.product.controller;


import com.atcs.product.model.Product;
import com.atcs.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/post")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @GetMapping("/service/{pincode}")
    public Integer serviceAbility(@PathVariable("pincode") String pincode)
    {
        return productService.servicable(pincode);
    }

    @GetMapping("/filter/{price1}/{price2}")
    public List<Product> filterProducts(@PathVariable("price1") Double price1, @PathVariable("price2") Double price2){
        return productService.filterProducts(price1,price2);
    }

    @GetMapping("/searchResults/{parameter}")
    public List<Product> searchProduct(@PathVariable("parameter") String parameter){
        return productService.getByParameter(parameter);
    }

    @GetMapping("/name/{name}")
    public Product findByName(@PathVariable("name") String name)
    {
        return productService.findByName(name);
    }

//    @GetMapping("/code/{code}")
//    public Product findByCode(@PathVariable("code") Long code)
//    {
//        return productService.findByProduct(code);
//    }

    @GetMapping("/brand/{brand}")
    public Product findByBrand(@PathVariable("brand") String brand)
    {
        return productService.findByBrandName(brand);
    }

//    @GetMapping("/prices")
//    public List<Long> getPrices(List<Long> ids)
//    {
//        return productService.getPrices(ids);
//    }
//
//    @GetMapping("/service")
//    public Integer serviceAbility(Long code,Long pincode)
//    {
//       return productService.servicable(code,pincode);
//    }

    @GetMapping("/all")
    public List<Product> getAll()
    {
        return  productService.getAll();
    }


}
