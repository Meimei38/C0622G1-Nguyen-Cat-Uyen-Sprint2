package yarnshop.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yarnshop.model.product.Product;

public interface IProductService{
    Page<Product> getAllProduct(Pageable pageable);
}
