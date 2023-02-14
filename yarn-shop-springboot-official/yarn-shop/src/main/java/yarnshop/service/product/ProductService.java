package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yarnshop.dto.ProductDto;
import yarnshop.model.product.Brand;
import yarnshop.model.product.Category;
import yarnshop.model.product.Product;
import yarnshop.repository.IBrandRepository;
import yarnshop.repository.ICategoryRepository;
import yarnshop.repository.IProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository categoryRepository;
    @Autowired
    IBrandRepository brandRepository;



    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable, String searchName, String searchCategory, String searchBrand, String searchPrice) {
        return productRepository.searchProduct(pageable, searchName, searchCategory, searchBrand, searchPrice);
    }

    @Override
    public ProductDto getProductDtoById(String idProduct) {
        return productRepository.getProductDtoById(idProduct);
    }
}
