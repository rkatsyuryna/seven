package com.seven.models.repositories;

import com.seven.models.UserRole;
import com.seven.models.domainobjects.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @Test
    public void testgetRoleByRoleNameRoleExist() throws Exception {

        Role role = this.repository.getRoleByRoleName(UserRole.ADMIN);

        Assert.assertNotNull(role);
    }
}
