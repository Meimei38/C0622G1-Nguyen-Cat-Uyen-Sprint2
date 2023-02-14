package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.model.product.Image;
import yarnshop.model.product.ProductDetail;
import yarnshop.service.product.IImageService;
import yarnshop.service.product.IProductDetailService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/product-detail")
public class ProductDetailController {
    @Autowired
    IProductDetailService productDetailService;

    @Autowired
    IImageService imageService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductDetail>> findProductDetailByProductId(@RequestParam(value = "idProduct") String productId) {
        List<ProductDetail> productDetailList = productDetailService.findByProductId(productId);
        if (productDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDetailList, HttpStatus.OK);
    }

    @GetMapping("/images")
    public ResponseEntity<List<Image>> findImagesByProductId(@RequestParam(value = "idProduct") String productId) {
        List<Image> imageList = imageService.findImagesByProductId(productId);
        if (imageList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(imageList, HttpStatus.OK);
    }
}
