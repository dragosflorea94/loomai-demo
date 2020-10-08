package com.loomai.demo.service;

import com.loomai.demo.domain.UserProfile;
import com.loomai.demo.exception.ErrorMessage;
import com.loomai.demo.exception.NotFoundException;
import com.loomai.demo.repository.UserProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(JUnit4.class)
public class UserProfileServiceImplTest {

    private static final Long ID = 123L;
    private static final Long ID2 = 234L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    private UserProfileServiceImpl userProfileService;

    @Mock
    private UserProfileRepository repository;

    @Before
    public void setUp() {
        initMocks(this);
        userProfileService = spy(new UserProfileServiceImpl(repository));
    }

    @Test
    public void createUserProfile_success() throws Exception {
        // Given
        UserProfile userProfile = buildTestUserProfile();
        userProfile.setId(null);
        when(repository.save(userProfile)).then((Answer<UserProfile>) invocationOnMock -> {
            UserProfile argument = invocationOnMock.getArgument(0);
            argument.setId(ID);
            return argument;
        });

        // When
        userProfile = userProfileService.save(userProfile);

        // Then
        assertEquals(ID, userProfile.getId());
        assertEquals(FIRST_NAME, userProfile.getFirstName());
        assertEquals(LAST_NAME, userProfile.getLastName());
        assertNotNull(userProfile.getUpdatedAt());
    }

    @Test
    public void getById_success() throws Exception {
        // Given
        UserProfile userProfile = buildTestUserProfile();
        when(repository.findById(ID)).thenReturn(Optional.of(userProfile));

        // When
        userProfile = userProfileService.getById(ID);

        // Then
        assertEquals(ID, userProfile.getId());
        assertEquals(FIRST_NAME, userProfile.getFirstName());
        assertEquals(LAST_NAME, userProfile.getLastName());
    }

    @Test
    public void getById_notFound() throws Exception {
        // Given
        UserProfile userProfile = buildTestUserProfile();
        when(repository.findById(ID2)).thenReturn(Optional.of(userProfile));

        // When
        try {
            userProfileService.getById(ID);
        } catch (NotFoundException ex) {
            // Then
            assertEquals(ErrorMessage.PROFILE_NOT_FOUND.getMsg(), ex.getMessage());

        }
    }

    private UserProfile buildTestUserProfile() {
        return UserProfile.builder().id(ID).firstName(FIRST_NAME).lastName(LAST_NAME).build();
    }
}
