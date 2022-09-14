package com.atcs.product.service;

import com.atcs.product.model.Pincode;
import com.atcs.product.model.Product;
import com.atcs.product.repo.PincodeRepo;
import com.atcs.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PincodeRepo pincodeRepo;

    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public Product findByName(String name) {
        Product pro = productRepo.findByProName(name);
        return pro;
    }



    @Override
    public Product findByBrandName(String brand) {
        Product pro = productRepo.findByProBrand(brand);
        return pro;
    }



    @Override
    public List<Product> getByParameter(String parameter) {
        List<Product> allProducts = productRepo.findAll();
        List<Product> searchedProducts = new ArrayList<Product>();
        for(Product product: allProducts){
            if(parameter.equalsIgnoreCase(product.getProName())) {
                searchedProducts.add(product);
            }
            else
            if(parameter.equalsIgnoreCase(product.getProBrand())){
                searchedProducts.add(product);
            }
            else
            if(parameter.equalsIgnoreCase(product.getProCode())) {
                searchedProducts.add(product);
            }
        }
        return searchedProducts;
    }

    @Override
    public Integer servicable(String pincode) {
        List<Pincode> pincodeList = pincodeRepo.findAll();

        for(Pincode pinObj: pincodeList){

            if(pincode.equals(pinObj.getPin())){
                return pinObj.getDays();
            }
        }
        return null;
    }

    @Override
    public List<Product> filterProducts(Double price1, Double price2) {
        List<Product> allProducts = productRepo.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product: allProducts){
            if(product.getProPrice()>=price1 && product.getProPrice()<=price2){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }



    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }


}
