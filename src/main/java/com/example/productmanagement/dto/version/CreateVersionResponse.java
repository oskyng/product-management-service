package com.example.productmanagement.dto.version;

import com.example.productmanagement.dto.GenericResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateVersionResponse extends GenericResponse {
    VersionDto version;
}
