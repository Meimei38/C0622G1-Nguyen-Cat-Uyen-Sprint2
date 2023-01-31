package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yarnshop.model.product.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
