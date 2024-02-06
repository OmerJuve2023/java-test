package omer.solutions.javatest.service.impl;

import omer.solutions.javatest.dto.request.InsertProductMasterRequest;
import omer.solutions.javatest.dto.request.UpdateProductMasterRequest;
import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.dto.response.MessageResponse;
import omer.solutions.javatest.exceptions.classes.NotFoundClassException;
import omer.solutions.javatest.model.ProductDetail;
import omer.solutions.javatest.model.ProductMaster;
import omer.solutions.javatest.repository.ProductMasterRepository;
import omer.solutions.javatest.service.interfaces.ProductMasterService;
import omer.solutions.javatest.util.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {
    private final ProductMasterRepository masterRepository;

    @Autowired
    public ProductMasterServiceImpl(ProductMasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = {Exception.class
                    , RuntimeException.class})
    @CacheEvict(value = "productMasterCache", allEntries = true)
    public MessageResponse insert(InsertProductMasterRequest request) {
        ProductMaster productMaster = ProductMaster.builder()
                .id(UUID.randomUUID())
                .price(request.getPrice())
                .category(request.getCategory())
                .dateCreated(LocalDate.now())
                .enable(true)
                .name(request.getName())
                .build();
        final ProductDetail productDetail = ProductDetail.builder()
                .id(UUID.randomUUID())
                .description(request.getDescription())
                .productMaster(productMaster)
                .build();
        productMaster.setProductDetail(productDetail);
        masterRepository.save(productMaster);
        return new MessageResponse(MessageInfo.CREATED_PRODUCT.toString());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = {Exception.class
                    , RuntimeException.class})
    @CacheEvict(value = "productMasterCache", allEntries = true)
    public MessageResponse update(UpdateProductMasterRequest request) {
        if (!masterRepository.existsById(request.getId())) {
            throw new NotFoundClassException(MessageInfo.NOT_FOUND_PRODUCT.toString());
        }
        ProductMaster master = masterRepository.searchProductMasterById(request.getId());
        ProductDetail detail = master.getProductDetail();

        master.setCategory(request.getCategory());
        master.setEnable(request.getEnable());
        master.setPrice(request.getPrice());
        master.setName(request.getName());
        detail.setDescription(request.getDescription());
        master.setProductDetail(detail);

        masterRepository.save(master);

        return new MessageResponse(MessageInfo.UPDATE_PRODUCT.toString());
    }

    @Override
    @Transactional(readOnly = true,
            rollbackFor = Exception.class)
    @Cacheable("productMasterCache")
    public GetProductMasterResponse getById(UUID request) {

        if (masterRepository.existsById(request)) {
            return masterRepository.findProductDetailsById(request);
        } else {
            throw new NotFoundClassException(MessageInfo.NOT_FOUND_PRODUCT.toString());
        }
    }

}