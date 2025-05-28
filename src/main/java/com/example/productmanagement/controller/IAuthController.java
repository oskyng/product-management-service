package com.example.productmanagement.controller;

import com.example.productmanagement.dto.AuthRequest;
import com.example.productmanagement.dto.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/v1"))
@Tag(name = "Auth")
public interface IAuthController {
    @Operation(
            summary = "Realiza login",
            parameters = { }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Operation executed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class)))
            }
    )
    @PostMapping(value = "auth/login",  produces = MediaType.APPLICATION_JSON_VALUE)
    AuthResponse login(@RequestBody AuthRequest authRequest);
}
