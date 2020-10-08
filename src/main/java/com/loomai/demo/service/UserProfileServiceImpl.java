package com.loomai.demo.service;

import com.loomai.demo.domain.UserProfile;
import com.loomai.demo.exception.ErrorMessage;
import com.loomai.demo.exception.NotFoundException;
import com.loomai.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile getById(Long id) {
        return getIfExistsOrThrow(id);
    }

    @Override
    public List<UserProfile> getAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        userProfile.setId(null);
        userProfile.setUpdatedAt(LocalDateTime.now());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        validateUserProfileExists(userProfile.getId());
        userProfile.setUpdatedAt(LocalDateTime.now());
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void delete(Long id) {
        validateUserProfileExists(id);
        userProfileRepository.deleteById(id);
    }

    private void validateUserProfileExists(Long id) {
        getIfExistsOrThrow(id);
    }

    private UserProfile getIfExistsOrThrow(Long id) {
        Optional<UserProfile> existingUserProfile = userProfileRepository.findById(id);
        if (!existingUserProfile.isPresent()) {
            throw new NotFoundException(ErrorMessage.PROFILE_NOT_FOUND.getMsg());
        }
        return existingUserProfile.get();
    }
}
