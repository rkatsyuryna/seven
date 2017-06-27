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

    @Test
    public void testGetUserByIdCallRepository() {
        long id = 123L;

        service.getUserById(id);

        verify(userRepository).findOne(id);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @Ignore("need refactoring")
    public void testGetAllUsersManagedBy() {
        String userName = "testUser";

        service.getAllUsersManagedBy(userName);

      //TODO :: need move to separate class and mock calls for  (SecurityUtils. isAdmin() & isOwner(
    }


}
