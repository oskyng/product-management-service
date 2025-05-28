package com.example.productmanagement.controller;

import com.example.productmanagement.dto.version.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/v1"))
@Tag(name = "Versiones")
public interface IVersionController {
    @Operation(
            summary = "Obtiene todas las versiones de un producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetVersionsResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping(value = "productos/{id}/versiones",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getVersions(
            @RequestParam int page,
            @PathVariable Long id
    );

    @Operation(
            summary = "Obtiene version por id",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetVersionByIdResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping(value = "versiones/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getVersionById(
            @PathVariable Long id
    );

    @Operation(
            summary = "Crea una nueva version al producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateVersionResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad input parameter"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PostMapping(value = "productos/{id}/versiones",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createVersion(
            @PathVariable Long id,
            @RequestBody @Valid CreateVersionRequest request
    );

    @Operation(
            summary = "Actualiza una version del producto",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateVersionResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad input parameter"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PutMapping(value = "versiones/{id}",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> updateVersion(
            @PathVariable Long id,
            @RequestBody @Valid UpdateVersionRequest request
    );

    @Operation(
            summary = "Elimina una version",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteVersionResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping(value = "versiones/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> DeleteVersion(@PathVariable Long id);
}
