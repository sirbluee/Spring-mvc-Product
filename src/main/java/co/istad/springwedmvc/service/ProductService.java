package co.istad.springwedmvc.service;

import co.istad.springwedmvc.dto.ProductCreateRequest;
import co.istad.springwedmvc.dto.ProductDto;
import co.istad.springwedmvc.dto.ProductEditRequest;


import java.util.List;

public interface ProductService {

    void deleteProductById(Integer id);

    void editProductByUuid(String uuid, ProductEditRequest request);

    void createNewProduct(ProductCreateRequest request);

    List<ProductDto> findProducts(String name, Boolean status);

    ProductDto findProductById(Integer id);

    ProductDto findProductByUuid(String uuid);
}
