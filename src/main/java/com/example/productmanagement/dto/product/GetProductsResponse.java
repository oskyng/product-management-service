package com.example.productmanagement.dto.product;

import com.example.productmanagement.dto.PagedResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetProductsResponse extends PagedResponse {
    List<ProductDto> products;
}
