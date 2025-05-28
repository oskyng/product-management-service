package com.example.productmanagement.dto.version;

import com.example.productmanagement.dto.PagedResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetVersionsResponse extends PagedResponse {
    List<VersionDto> version;
}
