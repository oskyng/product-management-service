package com.example.productmanagement.dto.version;

import com.example.productmanagement.model.StatusType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVersionRequest {
    @NotBlank(message = "El numero de la version no puede estar vacio")
    @Pattern(regexp = "^(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)(-(0|[1-9A-Za-z-][0-9A-Za-z-]*)(\\.[0-9A-Za-z-]+)*)?(\\+[0-9A-Za-z-]+(\\.[0-9A-Za-z-]+)*)?$", message = "El numero de la version debe tener el siguiente formato: 1.0.0")
    private String versionNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "La fecha de lanzamiento debe ser mayor a hoy")
    private Date releaseDate;
    private StatusType status;
    private String notes;
}
