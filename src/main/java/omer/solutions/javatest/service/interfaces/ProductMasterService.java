package omer.solutions.javatest.service.interfaces;

import omer.solutions.javatest.dto.request.InsertProductMasterRequest;
import omer.solutions.javatest.dto.request.UpdateProductMasterRequest;
import omer.solutions.javatest.dto.response.GetProductMasterResponse;
import omer.solutions.javatest.dto.response.MessageResponse;

import java.util.UUID;


public interface ProductMasterService {

    MessageResponse insert(InsertProductMasterRequest request);

    MessageResponse update(UpdateProductMasterRequest request);

    GetProductMasterResponse getById(UUID request);

}
