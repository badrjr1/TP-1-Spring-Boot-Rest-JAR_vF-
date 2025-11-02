package com.example.rest.controller;

import java.util.List;

import com.example.rest.model.Product;
import com.example.rest.service.IProdcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProductController {
    /**
     * @Autowired permet d'injecter le bean de type IProdcutService
     * (objet représentant la couche métier).
     * Ici, le Design Pattern qui est appliqué est l'IOC (Inversion Of Control).
     */
    @Autowired
    private IProdcutService service;
    /**
     * Pour chercher tous les produits
     */
    @GetMapping(value = "/products")
    //@GetMapping(value = "/products",,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getAll() {
        return service.getAll();
    }
    /**
     * Pour chercher un produit par son id
     */
    @GetMapping(value = "/products/{id}")
    //@GetMapping(value = "/products/{id}",,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Product getProductById(@PathVariable(value = "id") Long productId) {
        return service.getById(productId);
    }
    /**
     * Pour créer un nouveau produit
     ResponseEntity représente l'intégralité de la réponse HTTP : code d'état, en-têtes
     et corps . Par conséquent, nous pouvons l'utiliser pour configurer entièrement la
     réponse HTTP. Si nous voulons l'utiliser, nous devons la renvoyer depuis le point de
     terminaison ; Spring s'occupe du reste.
     */
    @PostMapping(value = "/products")
    //@PostMapping(value = "/products",,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createProdut(@Validated @RequestBody Product
                                                       product) {
        service.create(product);
        return new ResponseEntity<>("Product is created successfully",
                HttpStatus.CREATED);
    }
    /**
     * Pour modifier un produit par son id
     */
    @PutMapping(value = "/products/{id}")
    //@PutMapping(value = "/products/{id}",,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long
                                                        productId,
                                                @RequestBody Product product) {
        Product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.update(productId, product);
        return new ResponseEntity<>("Product is updated successsfully",
                HttpStatus.OK);
    }
    /**
     * Pour supprimer un produit par son id
     */
    @DeleteMapping(value = "/products/{id}")
    //@DeleteMapping(value = "/products/{id}",,produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long
                                                        productId) {
        Product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.delete(productId);
        return new ResponseEntity<>("Product is deleted successsfully",
                HttpStatus.OK);
    }
    public IProdcutService getService() {
        return service;
    }
    public void setService(IProdcutService service) {
        this.service = service;
    }
}
