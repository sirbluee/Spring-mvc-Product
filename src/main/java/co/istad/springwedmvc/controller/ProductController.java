package co.istad.springwedmvc.controller;

import co.istad.springwedmvc.dto.ProductCreateRequest;
import co.istad.springwedmvc.dto.ProductEditRequest;
import co.istad.springwedmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Validated
@Slf4j
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService; // Mark this as final

    // Delete product by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    // edit product by uuid
    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid, @RequestBody ProductEditRequest request){

        productService.editProductByUuid(uuid, request);

    }

    //create product
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        logger.info("Received request: {}", request);
        productService.createNewProduct(request);
    }

    // get all products
    @GetMapping
    public Map<String, Object> findProduct(@RequestParam(required = false, defaultValue = "")String name,
                                           @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        );
    }

    // find product by id
    @GetMapping("/{id}")
    public Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    // find product by uuid
    @GetMapping("/uuid/{uuid}")
    public Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }
}
