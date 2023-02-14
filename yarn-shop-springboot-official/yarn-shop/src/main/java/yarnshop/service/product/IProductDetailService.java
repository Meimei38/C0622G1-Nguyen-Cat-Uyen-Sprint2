package yarnshop.service.product;

import yarnshop.model.product.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    List<ProductDetail> findByProductId(String productId);

    ProductDetail findByProductDetailId(String productDetailId);
}
