package com.loomai.demo.converter;

import com.loomai.demo.domain.UserProfile;
import com.loomai.demo.dto.UserProfileDto;
import org.springframework.stereotype.Component;

@Component
public class UserProfileConverter {

    public UserProfileDto userProfileToDto(UserProfile userProfile) {
        return UserProfileDto.builder()
                .id(userProfile.getId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .alias(userProfile.getAlias())
                .country(userProfile.getCountry())
                .updatedAt(userProfile.getUpdatedAt())
                .build();
    }

    public UserProfile userProfileFromDto(UserProfileDto userProfileDto) {
        return UserProfile.builder()
                .id(userProfileDto.getId())
                .firstName(userProfileDto.getFirstName())
                .lastName(userProfileDto.getLastName())
                .alias(userProfileDto.getAlias())
                .country(userProfileDto.getCountry())
                .updatedAt(userProfileDto.getUpdatedAt())
                .build();
    }
}
