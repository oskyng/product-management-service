package com.example.productmanagement.dto.product;

import com.example.productmanagement.dto.GenericResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductResponse extends GenericResponse {
    ProductDto products;
}
