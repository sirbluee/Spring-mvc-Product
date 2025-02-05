package co.istad.springwedmvc.service.impl;

import co.istad.springwedmvc.dto.ProductCreateRequest;
import co.istad.springwedmvc.dto.ProductDto;
import co.istad.springwedmvc.dto.ProductEditRequest;
import co.istad.springwedmvc.model.Product;
import co.istad.springwedmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // Add this annotation to make it a Spring bean
@Slf4j
public class ProductServiceImpl implements ProductService {

    // product is domain model not dto
    private List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();

        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("iPhone 14 Pro Max");
        p1.setPrice(1300.0);
        p1.setQty(1);
        p1.setImportedDate(LocalDateTime.now());
        p1.setStatus(true);
        products.add(p1);

        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("Imac 2002");
        p2.setPrice(1300.0);
        p2.setQty(1);
        p2.setImportedDate(LocalDateTime.now());
        p2.setStatus(false);
        products.add(p2);
    }

    //create new product
    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setId(products.size()+1);
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setStatus(true);
        newProduct.setImportedDate(LocalDateTime.now());
        products.add(newProduct);
    }

    // get all products
    @Override
    public List<ProductDto> findProducts(String name, Boolean status) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty(),
                        product.getImportedDate(),
                        product.getStatus()
                ))
                .toList();
    }

    //delete product by id
    @Override
    public void deleteProductById(Integer id) {

        products = products.stream()
                .filter(product -> !product.getId().equals(id))
                .collect(Collectors.toList());
        log.info("Affected row: {}", products);
    }

    //edit product by uuid
    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        // check uuid if exist
        long count = products.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct -> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected row" + count);
    }

    // get product by id
    @Override
    public ProductDto findProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id) && product.getStatus().equals(true))
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty(),
                        product.getImportedDate(),
                        product.getStatus()
                )).findFirst().orElseThrow();
    }

    // get product by uuid
    @Override
    public ProductDto findProductByUuid(String uuid) {
        return products.stream()
                .filter(product -> product.getUuid().equals(uuid) && product.getStatus().equals(true))
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty(),
                        product.getImportedDate(),
                        product.getStatus()
                )).findFirst().orElseThrow();
    }
}
