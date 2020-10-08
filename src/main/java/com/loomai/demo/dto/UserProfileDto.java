package com.loomai.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserProfileDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String alias;
    private String country;
    private LocalDateTime updatedAt;
}
