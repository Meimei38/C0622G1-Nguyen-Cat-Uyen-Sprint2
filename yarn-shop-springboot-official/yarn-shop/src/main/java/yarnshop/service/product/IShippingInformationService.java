package yarnshop.service.product;

import yarnshop.model.shipping.ShippingInformation;

public interface IShippingInformationService {
    void save(ShippingInformation shippingInformation);
}
