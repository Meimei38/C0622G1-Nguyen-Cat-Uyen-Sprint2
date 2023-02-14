package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.product.Image;
import yarnshop.repository.ImageRepository;

import java.util.List;
@Service
public class ImageService implements IImageService{
    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<Image> findImagesByProductId(String productId) {
        return imageRepository.findImagesByProductId(productId);
    }
}
