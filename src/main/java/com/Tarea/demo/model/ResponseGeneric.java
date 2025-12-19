package com.Tarea.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneric<T> {
    private Boolean status;
    private String message;
    private T data;

    public ResponseGeneric(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
