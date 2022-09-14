package com.atcs.product.repo;

import com.atcs.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
    Product findByProName(String name);



    Product findByProBrand(String brand);


}
