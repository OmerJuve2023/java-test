package omer.solutions.javatest.service.impl;

import omer.solutions.javatest.dto.request.UpdateProductDetailRequest;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.exceptions.classes.NotFoundClassException;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.repository.ProductDetailRepository;
import omer.solutions.javatest.service.interfaces.ProductDetailService;
import omer.solutions.javatest.util.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository repository;

    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDetail getById(UUID request) {
        String cachedDescription = DetailCacheService.getDescriptionFromCache(request);

        if (cachedDescription != null) {
            ProductDetail productDetail = repository.findAllById(request);
            return new ProductDetail(request, cachedDescription, productDetail.getProductMaster());
        } else {
            if (repository.existsById(request)) {
                ProductDetail productDetail = repository.findAllById(request);
                DetailCacheService.putDescriptionInCache(request, productDetail.getDescription());
                return productDetail;
            } else {
                throw new NotFoundClassException(MessageInfo.NOT_FOUND_PRODUCT_DETAIL.toString());
            }
        }
    }

    @Override
    public MessageResponse update(UpdateProductDetailRequest request) {
        if (repository.existsById(request.getId())) {
            ProductDetail detail = repository.findAllById(request.getId());
            detail.setDescription(request.getDescription());
            repository.save(detail);

            DetailCacheService.putDescriptionInCache(request.getId(), request.getDescription());

            return new MessageResponse(MessageInfo.UPDATE_PRODUCT_DETAIL.toString());
        } else {
            throw new NotFoundClassException(MessageInfo.NOT_FOUND_PRODUCT_DETAIL.toString());
        }
    }
}
