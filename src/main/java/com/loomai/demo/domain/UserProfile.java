package com.loomai.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;
    // using some example fields, real user profile will have a lot more
    private String firstName;
    private String lastName;
    private String alias;
    private String country;
    private LocalDateTime updatedAt;

}