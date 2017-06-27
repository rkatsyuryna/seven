package com.seven.models.repositories;

import com.seven.models.domainobjects.Company;
import com.seven.models.domainobjects.Role;
import com.seven.models.domainobjects.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import javax.validation.ConstraintViolationException;

import java.util.Collection;

import static org.junit.Assert.*;


/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    private static final String FIRST_NAME = "testUserFirstName";
    private static final String LAST_NAME = "testUserLastName";
    private static final String EMAIL = "user@google.com";
    private static final String PHONE = "skypeMe";
    private static final String PWD = "Password1";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSaveUserWithoutCompanySuccessfully() throws Exception {
        this.entityManager.persist(new User(FIRST_NAME, LAST_NAME, EMAIL, PHONE, PWD));

        User user = this.repository.findUserByEmail(EMAIL);

        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertNull(user.getCompany());
    }

    //TODO : this test depends on company data specified by data.sql Fix it and use @SQL for test specific data
    @Test
    public void testSaveUserWithCompanySuccessfully() throws Exception {
        String companyName = "Seven";
        Company company = this.companyRepository.findCompanyByName(companyName);

        User newUser = new User(FIRST_NAME, LAST_NAME, EMAIL, PHONE, PWD);
        newUser.setCompany(company);
        this.entityManager.persist(newUser);

        Collection<User> users = this.repository.findUsersByCompany_Name(companyName);
        assertEquals(1, users.size());

        User user = users.iterator().next();
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertEquals(company, user.getCompany());
    }

    //TODO : this test depends on role data specified by data.sql Fix it and use @SQL for test specific data
    @Test
    public void testSaveUserWithRoleSuccessfully() throws Exception {
        Role role = this.roleRepository.findOne(1L);

        User newUser = new User(FIRST_NAME, LAST_NAME, EMAIL, PHONE, PWD);
        newUser.setRole(role);
        this.entityManager.persist(newUser);

        User user = this.repository.findUserByEmail(EMAIL);

        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PHONE, user.getPhone());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testSaveUserWithoutInvalidEmailThrowException() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("not a well-formed email address");

        this.entityManager.persist(new User(FIRST_NAME, LAST_NAME, "fake_email", PHONE, PWD));
    }

    //TODO : this test depends on data specified by data.sql Fix it and use @SQL for test specific data
    @Test
    public void testFindUsersByCompany_NameExist() throws Exception {
        Collection<User> users = this.repository.findUsersByCompany_Name("Company1");

        assertEquals(3, users.size());
    }

    @Test
    public void testFindUsersByCompany_NameNotExist() throws Exception {
        Collection<User> users = this.repository.findUsersByCompany_Name("CompanyX");

        assertEquals(0, users.size());
    }

}
