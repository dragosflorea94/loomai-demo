package com.loomai.demo.service;

import com.loomai.demo.domain.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile getById(Long id);

    List<UserProfile> getAll();

    UserProfile save(UserProfile userProfile);

    UserProfile update(UserProfile userProfile);

    void delete(Long id);
}
