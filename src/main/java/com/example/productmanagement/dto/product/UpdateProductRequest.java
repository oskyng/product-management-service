package com.example.productmanagement.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del producto debe tener entre 3 y 60 caracteres")
    private String name;
    @Size(max = 255, message = "La descripcion del producto debe tener maximo 255 caracteres")
    private String description;
}
