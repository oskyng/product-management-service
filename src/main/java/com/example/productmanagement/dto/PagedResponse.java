package com.example.productmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagedResponse extends GenericResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalRows;
}
