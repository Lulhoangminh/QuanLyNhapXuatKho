package com.example.bill_management.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Builder.Default
    private long code = 1000;
    private String message;
    private T result;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date timestamp = new Date();

    private String path;
}
