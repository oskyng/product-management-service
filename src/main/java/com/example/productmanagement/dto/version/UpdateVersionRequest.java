package com.example.productmanagement.dto.version;

import com.example.productmanagement.model.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UpdateVersionRequest {
    private String versionNumber;
    private Date releaseDate;
    private StatusType status;
    private String notes;
}
