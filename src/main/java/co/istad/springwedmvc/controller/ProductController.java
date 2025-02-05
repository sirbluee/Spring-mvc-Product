package co.istad.springwedmvc.controller;

import co.istad.springwedmvc.dto.ProductCreateRequest;
import co.istad.springwedmvc.dto.ProductEditRequest;
import co.istad.springwedmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

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
    void createNewProduct(@RequestBody ProductCreateRequest request){
        System.out.println("REQUEST" + request);
        productService.createNewProduct(request);
    }

    @GetMapping
    public Map<String, Object> findProduct(@RequestParam(required = false, defaultValue = "")String name,
                                           @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return Map.of(
                "message", "Products have been found",
                "data", productService.findProducts(name, status)
        );
    }

    @GetMapping("/{id}")
    public Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    public Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "Product has been found",
                "data", productService.findProductByUuid(uuid)
        );
    }
}
