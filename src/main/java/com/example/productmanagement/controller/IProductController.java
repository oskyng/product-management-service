package com.example.productmanagement.controller;

import com.example.productmanagement.dto.product.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/v1"))
@Tag(name = "Productos")
public interface IProductController {
    @Operation(
            summary = "Obtiene todos los productos",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetProductsResponse.class)))
            }
    )
    @GetMapping(value = "productos",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getProducts(
            @RequestParam int page,
            @RequestParam(required = false) String name
    );

    @Operation(
            summary = "Obtiene productos por id",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetProductByIdResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping(value = "productos/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getProductById(
            @PathVariable Long id
    );

    @Operation(
            summary = "Crea nuevo producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateProductResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad input parameter"),
                    @ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PostMapping(value = "productos",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createProduct(@RequestBody @Valid CreateProductRequest request);

    @Operation(
            summary = "Actualiza un producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateProductResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad input parameter"),
                    @ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
    @PutMapping(value = "productos/{id}",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid CreateProductRequest request);

    @Operation(
            summary = "Elimina un producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteProductResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping(value = "productos/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> DeleteProduct(@PathVariable Long id);
}
