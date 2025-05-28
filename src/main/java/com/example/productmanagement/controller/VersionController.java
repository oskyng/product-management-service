package com.example.productmanagement.controller;

import com.example.productmanagement.dto.version.CreateVersionRequest;
import com.example.productmanagement.dto.version.UpdateVersionRequest;
import com.example.productmanagement.service.IVersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class VersionController implements IVersionController {
    private final IVersionService versionService;

    public VersionController(IVersionService versionService) {
        this.versionService = versionService;
    }


    @Override
    public ResponseEntity<Object> getVersions(int page, Long id) {
        log.debug("Servicio: getVersions()");
        return ResponseEntity.ok(versionService.getVersions(page, id));
    }

    @Override
    public ResponseEntity<Object> getVersionById(Long id) {
        return ResponseEntity.ok(versionService.getVersionById(id));
    }

    @Override
    public ResponseEntity<Object> createVersion(Long id, CreateVersionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(versionService.createVersion(id, request));
    }

    @Override
    public ResponseEntity<Object> updateVersion(Long id, UpdateVersionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(versionService.updateVersion(id, request));
    }

    @Override
    public ResponseEntity<Object> DeleteVersion(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(versionService.deleteVersion(id));
    }
}
