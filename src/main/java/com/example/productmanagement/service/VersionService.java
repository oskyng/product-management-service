package com.example.productmanagement.service;

import com.example.productmanagement.dto.version.*;
import com.example.productmanagement.exception.ProductNotFoundException;
import com.example.productmanagement.model.Product;
import com.example.productmanagement.model.Version;
import com.example.productmanagement.repository.IProductRepository;
import com.example.productmanagement.repository.IVersionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VersionService implements IVersionService {
    @Value("${items.per.page}")
    private int itemsPerPage;

    private final IVersionRepository versionRepository;
    private final IProductRepository productRepository;

    public VersionService(IVersionRepository versionRepository, IProductRepository productRepository) {
        this.versionRepository = versionRepository;
        this.productRepository = productRepository;
    }

    @Override
    public GetVersionsResponse getVersions(int page, Long productId) {
        log.debug("Servicio: getVersions()");
        List<VersionDto> versionDtos = new ArrayList<>();

        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page - 1, itemsPerPage, sort);

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id - " + productId));
        Page<Version> versions = versionRepository.findAllByProduct(pageable, product);;

        versions.forEach(version -> {
            versionDtos.add(convertVersionDto(version));
        });

        GetVersionsResponse response = new GetVersionsResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());
        response.setPageNumber(page);
        response.setPageSize(itemsPerPage >= versions.getNumberOfElements() ? itemsPerPage : versions.getNumberOfElements());
        response.setTotalPages(versions.getTotalPages());
        response.setTotalRows(versionDtos.size());
        response.setVersion(versionDtos);

        return response;
    }

    @Override
    public GetVersionByIdResponse getVersionById(Long id) {
        log.debug("Servicio: getVersionById({})", id);
        Version version = versionRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Version not found with id - " + id));

        GetVersionByIdResponse response = new GetVersionByIdResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());
        response.setVersion(convertVersionDto(version));
        return response;
    }

    @Override
    @Transactional
    public CreateVersionResponse createVersion(Long productId, CreateVersionRequest request) {
        log.debug("Servicio: createVersion({})", productId);

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id - " + productId));
        Version version = new Version();
        version.setProduct(product);
        version.setVersionNumber(request.getVersionNumber());
        version.setStatus(request.getStatus());
        version.setReleaseDate(request.getReleaseDate());
        version.setNotes(request.getNotes());
        version.setCreatedAt(LocalDateTime.now());

        Version savedVersion = versionRepository.save(version);

        CreateVersionResponse response = new CreateVersionResponse();
        response.setCode(HttpStatus.CREATED.value());
        response.setDescription(HttpStatus.CREATED.getReasonPhrase());
        response.setVersion(convertVersionDto(savedVersion));

        return response;
    }

    @Override
    public UpdateVersionResponse updateVersion(Long id, UpdateVersionRequest request) {
        log.debug("Servicio: updateVersion({})", id);
        Version version = versionRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Version not found with id - " + id));

        if (request.getVersionNumber() != null && !request.getVersionNumber().isEmpty()) {
            version.setVersionNumber(request.getVersionNumber());
        }

        if (request.getNotes() != null && !request.getNotes().isEmpty()) {
            version.setNotes(request.getNotes());
        }

        if (request.getStatus() != null) {
            version.setStatus(request.getStatus());
        }

        if (request.getReleaseDate() != null) {
            version.setReleaseDate(request.getReleaseDate());
        }

        version.setUpdatedAt(LocalDateTime.now());

        Version updatedVersion = versionRepository.save(version);

        UpdateVersionResponse response = new UpdateVersionResponse();
        response.setCode(HttpStatus.CREATED.value());
        response.setDescription(HttpStatus.CREATED.getReasonPhrase());
        response.setVersion(convertVersionDto(updatedVersion));

        return response;
    }

    @Override
    public DeleteVersionResponse deleteVersion(Long id) {
        log.debug("Servicio: deleteVersion({})", id);
        Version version = versionRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Version not found with id - " + id));

        versionRepository.delete(version);

        DeleteVersionResponse response = new DeleteVersionResponse();
        response.setCode(HttpStatus.OK.value());
        response.setDescription(HttpStatus.OK.getReasonPhrase());

        return response;
    }

    private VersionDto convertVersionDto(Version version) {
        VersionDto versionDto = new VersionDto();
        versionDto.setId(version.getId());
        versionDto.setVersionNumber(version.getVersionNumber());
        versionDto.setStatus(version.getStatus());
        versionDto.setReleaseDate(version.getReleaseDate());
        versionDto.setNotes(version.getNotes());

        return versionDto;
    }
}
