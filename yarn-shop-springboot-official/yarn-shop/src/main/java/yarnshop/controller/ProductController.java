package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import yarnshop.dto.ProductDto;
import yarnshop.model.product.Brand;
import yarnshop.model.product.Category;
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
//    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ResponseEntity<Page<Product>> getListProducts(@PageableDefault(value = 5) Pageable pageable,
                                                         @RequestParam(value = "searchName") String searchName,
                                                         @RequestParam(value = "searchCategory") String searchCategory,
                                                         @RequestParam(value = "searchBrand") String searchBrand,
                                                         @RequestParam(value = "searchPrice") String searchPrice) {
        Page<Product> productPage = productService.getAllProduct(pageable, searchName, searchCategory, searchBrand, searchPrice);
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getListCategory() {
        List<Category> categories = productService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/brand")
    public ResponseEntity<List<Brand>> getListBrand() {
        List<Brand> brands = productService.getAllBrand();
        if (brands.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<ProductDto> getProductDtoById(@RequestParam(value = "idProduct") String idProduct) {
        ProductDto productDto = productService.getProductDtoById(idProduct);
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
