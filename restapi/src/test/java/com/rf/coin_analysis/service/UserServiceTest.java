package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.UserRegisterRequest;
import com.rf.coin_analysis.exception.NoAuthorityException;
import com.rf.coin_analysis.exception.NotFoundException;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.UserRepository;
import com.rf.coin_analysis.security.UserIdentityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserIdentityManager userIdentityManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private User mockUser;

    @Mock
    private UserRegisterRequest mockRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldReturnSuccess_WhenUserIsRegistered() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String name = "Test User";
        UserRegisterRequest request = new UserRegisterRequest(email, password, name);

        // Act
        ApiResponse<Void> response = userService.registerUser(request);

        // Assert
        assertEquals("Kayıt Başarılı", response.getMessage());
        verify(userRepository, times(1)).save(any(User.class));  // save metodunun çağrıldığını doğrula
    }

    @Test
    void delete_ShouldReturnSuccess_WhenUserIsDeleted() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(mockUser));
        when(userIdentityManager.isAuthorized(any(), eq(userId))).thenReturn(true);

        // Act
        ApiResponse<Void> response = userService.delete(userId);

        // Assert
        assertEquals("Kullanıcı silindi", response.getMessage());
        verify(userRepository, times(1)).delete(mockUser);
    }

    @Test
    void delete_ShouldThrowNoAuthorityException_WhenUserIsNotAuthorized() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(mockUser));
        when(userIdentityManager.isAuthorized(any(), eq(userId))).thenReturn(false);

        // Act & Assert
        assertThrows(NoAuthorityException.class, () -> userService.delete(userId));
        verify(userRepository, times(0)).delete(any(User.class));  // delete metodunun çağrılmadığını doğrula
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(mockUser));

        // Act
        User user = userService.findById(userId);

        // Assert
        assertNotNull(user);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findById_ShouldThrowNotFoundException_WhenUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.findById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenEmailExists() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(mockUser));

        // Act
        UserDetails userDetails = userService.loadUserByUsername(email);

        // Assert
        assertNotNull(userDetails);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsername_ShouldThrowNotFoundException_WhenEmailNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.loadUserByUsername(email));
        verify(userRepository, times(1)).findByEmail(email);
    }
}
