package org.springframework.samples.petclinic.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  Integer id;
//    todo viene heredado de BaseEntity   
    @NotNull
    @Size(min = 3, max= 50, message = "name must be between 3 and 50 characters")
    String name;
    
    @NotNull
    @Min(value = 0)
    double price;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "product_type_id")
    ProductType productType;
}
