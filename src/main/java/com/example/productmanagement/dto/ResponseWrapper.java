package com.example.productmanagement.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonPropertyOrder({ "status", "message", "timestamp", "cantidad", "data" })
public class ResponseWrapper<T> {
    private int status;
    private String message;
    private int cantidad;
    private LocalDateTime timestamp;
    private List<T> data;

    public ResponseWrapper(int status, String message, int cantidad, List<T> data) {
        this.status = status;
        this.message = message;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}
