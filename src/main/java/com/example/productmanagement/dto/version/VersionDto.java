package com.example.productmanagement.dto.version;

import com.example.productmanagement.dto.product.ProductDto;
import com.example.productmanagement.model.StatusType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersionDto {
    private Long id;
    private String versionNumber;
    private Date releaseDate;
    private StatusType status;
    private String notes;
}
