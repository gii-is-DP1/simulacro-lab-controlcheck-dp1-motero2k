package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findAll();
    @Query("SELECT pt FROM ProductType pt")
    List<ProductType> findAllProductTypes();
    @Query("SELECT pt FROM ProductType pt WHERE pt.name = :name")
    ProductType findProductTypeByName(@Param("name") String name);
    
    
//    Product findById(int id);
    Product findByName(String name);
    Product save(Product p);
    
    @Query("SELECT p FROM Product p WHERE p.price < :price")
	List<Product> findByPriceLessThan(@Param("price") double price);
	
    // this is for making a product view
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product findProductById(@Param("productId") Integer productId);
}
