package yarnshop.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yarnshop.dto.ProductDto;
import yarnshop.model.product.Brand;
import yarnshop.model.product.Category;
import yarnshop.model.product.Product;

import java.util.List;

public interface IProductService{
   

    List<Category> getAllCategories();

    List<Brand> getAllBrand();

    Page<Product> getAllProduct(Pageable pageable, String searchName, String searchCategory, String searchBrand, String searchPrice);

    ProductDto getProductDtoById(String idProduct);
}
