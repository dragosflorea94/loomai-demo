package com.loomai.demo.controller;

import com.loomai.demo.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserProfileController {

    ResponseEntity<UserProfileDto> getUserProfile(Long id);

    ResponseEntity<List<UserProfileDto>> getAllUserProfiles();

    ResponseEntity<UserProfileDto> createUserProfile(UserProfileDto userProfileDto);

    ResponseEntity<UserProfileDto> updateUserProfile(UserProfileDto userProfileDto);

    void deleteUserProfile(Long id);

}
