package com.agencia.Crud.Controller;

import com.agencia.Crud.Model.Product;
import com.agencia.Crud.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> editProduct(@RequestBody Product product){
        return this.productService.saveProduct(product);
    }

    @DeleteMapping(path  = "{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);
    }

    }




