package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarnshop.model.product.Product;
import yarnshop.service.product.IProductService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<Page<Product>>getListProducts(@PageableDefault(value = 5)Pageable pageable) {
        Page<Product> productPage = productService.getAllProduct(pageable);
        if (productPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}