package com.seven.services;

import com.seven.models.repositories.CompanyRepository;
import com.seven.models.repositories.RoleRepository;
import com.seven.models.repositories.UserRepository;
import com.seven.services.impl.UserServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserService service = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CurrentUserService currentUserService;

    @Test
    public void testGetUserByIdCallRepository() {
        long id = 123L;

        service.getUserById(id);

        verify(userRepository).findOne(id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetAllUsersManagedByForAdminReturnEmpty() {
        String userName = "testUser";

        when(currentUserService.isAdminUser()).thenReturn(true);
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        service.getAllUsersManagedBy(userName);

        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

}
