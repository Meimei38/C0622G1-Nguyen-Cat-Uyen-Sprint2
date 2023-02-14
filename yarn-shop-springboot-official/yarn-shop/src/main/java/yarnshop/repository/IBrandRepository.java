package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yarnshop.model.product.Brand;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IBrandRepository extends JpaRepository<Brand, Integer> {
}
