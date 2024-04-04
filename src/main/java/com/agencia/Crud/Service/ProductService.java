package com.agencia.Crud.Service;

import com.agencia.Crud.Model.Product;
import com.agencia.Crud.Repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> datos = new HashMap<>();
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> saveProduct(Product product) {

        Optional<Product> res = productRepository.findProductByName(product.getName());

        if (res.isPresent() && product.getId()==null) {
            datos.put("error", true);
            datos.put("message","Ya existe un objeto con ese nombre");
            return new ResponseEntity<>(
                    datos, HttpStatus.CONFLICT
            );
        }

        productRepository.save(product);
        datos.put("data", product);
        datos.put("message","el producto se guardo con exito");
        if (product.getId()!=null){
            datos.put("message","el producto se actualizo con exito");
        }
        return new ResponseEntity<>(
                datos, HttpStatus.CREATED
        );

    }

    public ResponseEntity<Object> deleteProduct(Long id){
        boolean existe = this.productRepository.existsById(id);
        if(!existe){
            datos.put("error",true) ;
            datos.put("message","el producto que desea eliminar no existe");
            return new ResponseEntity<>(
                    datos, HttpStatus.NOT_FOUND
            );
        }
        this.productRepository.deleteById(id);
        datos.put("message","el producto que desea eliminar no existe");
        return new ResponseEntity<>(
                datos, HttpStatus.ACCEPTED);
    }
}
