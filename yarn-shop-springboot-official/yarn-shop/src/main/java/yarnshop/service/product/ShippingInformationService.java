package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.shipping.ShippingInformation;
import yarnshop.repository.IShippingInformationRepository;

@Service
public class ShippingInformationService implements IShippingInformationService {
    @Autowired
    IShippingInformationRepository shippingInformationRepository;

    @Override
    public void save(ShippingInformation shippingInformation) {
        shippingInformationRepository.save(shippingInformation);
    }
}
