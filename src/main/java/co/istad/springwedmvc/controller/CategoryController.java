package co.istad.springwedmvc.controller;

import co.istad.springwedmvc.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    // customize swagger
    @Operation(summary = "Get all Product Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/products/categories")
    Map<String, Object> findCategories(){
        Map<String, Object> data= new HashMap<>();
        data.put("message", "Categories have been found.");
        data.put("data", List.of("Education", "Entertainment"));
                return data;
    }
}
