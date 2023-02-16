package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yarnshop.model.shipping.ShippingInformation;
@Repository
public interface IShippingInformationRepository extends JpaRepository<ShippingInformation, Integer> {
}
