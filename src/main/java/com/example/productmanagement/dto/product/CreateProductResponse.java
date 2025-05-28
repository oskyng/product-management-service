package com.example.productmanagement.dto.product;

import com.example.productmanagement.dto.GenericResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductResponse extends GenericResponse {
    ProductDto products;
}
