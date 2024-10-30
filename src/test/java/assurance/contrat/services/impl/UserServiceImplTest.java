package assurance.contrat.services.impl;

import assurance.contrat.model.entities.User;
import assurance.contrat.repository.UserRep;
import assurance.contrat.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRep userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User userToSave = new User();

        userService.saveUser(userToSave);

        verify(userRepository, times(1)).save(userToSave); }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(mockUser);

        User user = userService.getUserById(userId);

        assertNotNull(user);
        assertEquals(userId, user.getId());
        verify(userRepository, times(1)).findById(userId);}

    @Test
    void testGetAllUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll(); }

    @Test
    void testAuthenticate_Success() {
        String email = "test@example.com";
        String password = "password";
        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(password);
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        User authenticatedUser = userService.authenticate(email, password);

        assertNotNull(authenticatedUser);
        assertEquals(email, authenticatedUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email); }

    @Test
    void testAuthenticate_Failure() {
        String email = "wrong@example.com";
        String password = "password";
        when(userRepository.findByEmail(email)).thenReturn(null);

        User authenticatedUser = userService.authenticate(email, password);

        assertNull(authenticatedUser);
        verify(userRepository, times(1)).findByEmail(email); }
}
