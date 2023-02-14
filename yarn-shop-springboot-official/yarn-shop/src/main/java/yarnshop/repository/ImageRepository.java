package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yarnshop.model.product.Image;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select image.* from image " +
            "where product_id =:productId and is_delete=0", nativeQuery = true)
    List<Image> findImagesByProductId(String productId);
}
