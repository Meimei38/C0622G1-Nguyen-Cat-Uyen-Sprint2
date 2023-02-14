package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import yarnshop.model.product.Image;

import java.util.List;

public interface IImageService {

    List<Image> findImagesByProductId(String productId);
}
