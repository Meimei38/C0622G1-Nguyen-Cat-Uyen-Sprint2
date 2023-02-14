package yarnshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.dto.ProductDto;
import yarnshop.model.product.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
@Query(value = "select * from product\n" +
        "where product.category_id like %:searchCategory% " +
        "and product.brand_id like %:searchBrand% " +
        "and product.name like %:searchName% " +
        "and product.price <=:searchPrice and is_delete = 0", nativeQuery = true)
    Page<Product> searchProduct(Pageable pageable, @Param("searchName") String searchName,
                                @Param("searchCategory") String searchCategory,
                                @Param("searchBrand") String searchBrand,
                                @Param("searchPrice") String searchPrice);
    @Query(value = "select product.id as productId, \n" +
            "product.description as productDescription, \n" +
            "product.name as productName, \n" +
            "product.price as productPrice, \n" +
            "product.weight as productWeight, \n" +
            "brand.name as brandName, \n" +
            "category.name as categoryName \n" +
            "from product\n" +
            "join brand on  product.brand_id = brand.id\n" +
            "join category on product.category_id = category.id\n" +
            "join discount on product.discount_id = discount.id\n" +
            "where product.is_delete = 0 and product.id =:idProduct", nativeQuery = true)
    ProductDto getProductDtoById(@Param("idProduct") String idProduct);
}
