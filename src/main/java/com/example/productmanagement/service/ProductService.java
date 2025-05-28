package com.example.productmanagement.service;

import com.example.productmanagement.dto.product.*;
import com.example.productmanagement.exception.ProductNotFoundException;
import com.example.productmanagement.model.Product;
import com.example.productmanagement.repository.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService implements IProductService {
    @Value("${items.per.page}")
    private int itemsPerPage;
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GetProductsResponse getProducts(int page, String name) {
        log.debug("Servicio: getProducts()");
        List<ProductDto> productDtos = new ArrayList<>();

        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, itemsPerPage, sort);
        Page<Product> products = null;

        if (name != null && !name.isEmpty()) {
            pageable = PageRequest.of(page, itemsPerPage, sort);
            products = productRepository.findAllByNameAndDeletedAtIsNotNull(pageable, name);
        } else {
            products = productRepository.findAllByDeletedAtIsNotNull(pageable);
        }

        products.forEach(product -> {
            productDtos.add(convertProductToDto(product));
        });

        GetProductsResponse response = new GetProductsResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());
        response.setPageNumber(page);
        response.setPageSize(itemsPerPage >= products.getNumberOfElements() ? itemsPerPage : products.getNumberOfElements());
        response.setTotalPages(products.getTotalPages());
        response.setTotalRows(productDtos.size());
        response.setProducts(productDtos);

        return response;
    }

    @Override
    public GetProductByIdResponse getProductById(Long id) {
        log.debug("Servicio: getProductById({})", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id - " + id));

        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());
        response.setProduct(convertProductToDto(product));

        return response;
    }

    @Override
    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest request) {
        log.debug("Servicio: createProduct({})", request.getName());
        Optional<Product> foundProduct = productRepository.findByNameAndDeletedAtIsNotNull(request.getName());

        ProductDto productDto = null;

        if (foundProduct.isPresent()) {
            log.debug("Producto encontrado");
            Product product = foundProduct.get();
            productDto = convertProductToDto(product);
        } else {
            log.debug("Producto no encontrado, creando nuevo producto");
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setCreatedAt(LocalDateTime.now());
            product.setDeletedAt(LocalDateTime.now());
            Product savedProduct = productRepository.save(product);
            productDto = convertProductToDto(savedProduct);
        }

        CreateProductResponse response = new CreateProductResponse();
        response.setCode(HttpStatus.CREATED.value());
        response.setDescription(HttpStatus.CREATED.getReasonPhrase());
        response.setProducts(productDto);

        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, CreateProductRequest request) {
        log.debug("Servicio: updateProduct({})", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id - " + id));

        if (request.getName() != null && !request.getName().isEmpty()) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            product.setDescription(request.getDescription());
        }
        product.setUpdatedAt(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);

        UpdateProductResponse response = new UpdateProductResponse();
        response.setCode(HttpStatus.CREATED.value());
        response.setDescription(HttpStatus.CREATED.getReasonPhrase());
        response.setProducts(convertProductToDto(updatedProduct));

        return response;
    }

    @Override
    public DeleteProductResponse deleteProduct(Long id) {
        log.debug("Servicio: deleteProduct({})", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id - " + id));
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);

        DeleteProductResponse response = new DeleteProductResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());

        return response;
    }

    private ProductDto convertProductToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
