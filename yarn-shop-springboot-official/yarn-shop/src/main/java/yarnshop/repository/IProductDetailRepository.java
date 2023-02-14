package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.model.product.ProductDetail;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    @Query(value = "select product_detail.* " +
            "from product_detail " +
            "where product_id =:productId", nativeQuery = true)
    List<ProductDetail> findByProductId(@Param("productId") String productId);

    @Query(value = "select * from product_detail" +
            " where product_detail.id =:productDetailId and is_delete=0", nativeQuery = true)
    ProductDetail findByProductDetailId(@Param("productDetailId") String productDetailId);
}
