package com.loomai.demo.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestError {

    private String message;
    private int status;
}
