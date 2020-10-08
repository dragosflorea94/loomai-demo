package com.loomai.demo.controller;

import com.loomai.demo.converter.UserProfileConverter;
import com.loomai.demo.domain.UserProfile;
import com.loomai.demo.dto.UserProfileDto;
import com.loomai.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/userprofile", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserProfileControllerImpl {

    private final UserProfileService userProfileService;
    private final UserProfileConverter converter;

    @Autowired
    public UserProfileControllerImpl(UserProfileService userProfileService,
                                     UserProfileConverter converter) {
        this.userProfileService = userProfileService;
        this.converter = converter;
    }

    /***
     * Get a user profile by id
     *
     * @param id id of the requested user profile
     * @return UserProfileDto object containing the user profile of the given id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable("id") Long id) {
        return ResponseEntity.ok(converter.userProfileToDto(userProfileService.getById(id)));
    }

    /***
     * Get all existing user profiles
     *
     * @return List of UserProfileDto objects containing all existing user profiles
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserProfileDto>> getAllUserProfiles() {
        return ResponseEntity.ok(userProfileService
                .getAll()
                .stream()
                .map(converter::userProfileToDto)
                .collect(Collectors.toList()));
    }

    /***
     * Create a new user profile
     *
     * @param userProfileDto object containing the information for the user profile to be created
     * @return UserProfileDto object containing the information of the created user profile
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto) {
        if (userProfileDto == null) {
            return ResponseEntity.badRequest().build();
        }
        final UserProfile userProfile = converter.userProfileFromDto(userProfileDto);
        return ResponseEntity.ok(converter.userProfileToDto(userProfileService.save(userProfile)));
    }

    /***
     * Update an existing user profile, assuming a profile with the associated id exists
     *
     * @param userProfileDto object containing the new information for the user profile to be updated
     * @return UserProfileDto object containing the information of the updated user profile
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<UserProfileDto> updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        if (userProfileDto == null) {
            return ResponseEntity.badRequest().build();
        }
        final UserProfile userProfile = converter.userProfileFromDto(userProfileDto);
        UserProfile updatedUserProfile = userProfileService.update(userProfile);
        return ResponseEntity.ok(converter.userProfileToDto(updatedUserProfile));
    }

    /***
     * Delete a user profile, assuming a profile with the given id exists
     * @param id Id corresponding to the user profile to be deleted
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserProfile(@PathVariable("id") Long id) {
        userProfileService.delete(id);
    }
}
