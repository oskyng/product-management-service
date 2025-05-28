package com.example.productmanagement.service;

import com.example.productmanagement.dto.version.*;

public interface IVersionService {
    GetVersionsResponse getVersions(int page, Long productId);
    GetVersionByIdResponse getVersionById(Long id);
    CreateVersionResponse createVersion(Long productId, CreateVersionRequest request);
    UpdateVersionResponse updateVersion(Long id, UpdateVersionRequest request);
    DeleteVersionResponse deleteVersion(Long id);
}
