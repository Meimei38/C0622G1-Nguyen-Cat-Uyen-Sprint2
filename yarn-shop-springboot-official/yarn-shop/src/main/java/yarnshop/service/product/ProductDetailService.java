package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.product.ProductDetail;
import yarnshop.repository.IProductDetailRepository;
import yarnshop.repository.IProductRepository;

import java.util.List;

@Service
public class ProductDetailService implements IProductDetailService{
    @Autowired
    IProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findByProductId(String productId) {
        return productDetailRepository.findByProductId(productId);
    }

    @Override
    public ProductDetail findByProductDetailId(String productDetailId) {
        return productDetailRepository.findByProductDetailId(productDetailId);
    }

    @Override
    public void saveProductDetail(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }
}
