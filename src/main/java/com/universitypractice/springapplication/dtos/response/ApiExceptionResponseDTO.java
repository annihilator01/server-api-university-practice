package com.universitypractice.springapplication.dtos.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.universitypractice.springapplication.serializers.CustomGMTDateTimeSerializer;
import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class ApiExceptionResponseDTO {

    @JsonSerialize(using = CustomGMTDateTimeSerializer.class)
    @NonNull
    private ZonedDateTime timestamp;

    @NonNull
    private Integer statusCode;

    @NonNull
    private String error;

    @NonNull
    private String message;

    @NonNull
    private String path;
}
