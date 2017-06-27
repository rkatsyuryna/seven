package com.seven.services.impl;

import com.seven.SecurityUtils;
import com.seven.controllers.model.UserForm;
import com.seven.exceptions.BusinessSmthException;
import com.seven.models.UserRole;
import com.seven.models.domainobjects.Company;
import com.seven.models.domainobjects.Role;
import com.seven.models.domainobjects.User;
import com.seven.models.repositories.CompanyRepository;
import com.seven.models.repositories.RoleRepository;
import com.seven.models.repositories.UserRepository;
import com.seven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }

    @Override
    public Collection<User> getAllUsersManagedBy(String userName) {
        final Collection<User> users = new LinkedList<>();
        if(SecurityUtils.isAdmin()) {
            users.addAll((Collection<? extends User>) userRepository.findAll());
        }
        if(SecurityUtils.isOwner()) {

            Optional<User> currUser = getUserByEmail(userName);

            currUser.ifPresent(currUsr -> getAllUsersByCompany(currUsr.getCompany().getName()).stream().filter(user -> !user.isOwner()).forEach(users::add));
        }

        return users;
    }

    @Override
    public Collection<User> getAllUsersByCompany(String companyName) {
        return userRepository.findUsersByCompany_Name(companyName);
    }

    @Override
    @Transactional
    public User createManagedUser(String userName, UserForm newUser) {
        User user;

        Company company = companyRepository.findCompanyByName(newUser.getCompany());
        Role role = roleRepository.getRoleByRoleName(newUser.getRole());

        if (SecurityUtils.isAdmin() && SecurityUtils.isOwner()) {
            user = User.builder()
                    .firstName(newUser.getFirstName())
                    .lastName(newUser.getLastName())
                    .email(newUser.getEmail())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .role(role)
                    .company(company).build();

            return userRepository.save(user);
        } else if (SecurityUtils.isOwner()) {
            Optional<User> logedinUser = getUserByEmail(userName);

            logedinUser.filter(isCompanyNameAcceptable(newUser.getCompany())).orElseThrow(() -> new BusinessSmthException(String.format("Company owner %s has not privileges to create user %s for company", userName, newUser.getEmail(), newUser.getCompany())));

            if(!isRoleAcceptable(newUser.getRole())) {
                throw new BusinessSmthException("User can not be created by Company owner with provided role");
            }

            user = User.builder()
                    .firstName(newUser.getFirstName())
                    .lastName(newUser.getLastName())
                    .email(newUser.getEmail())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .role(role)
                    .company(company).build();

            return userRepository.save(user);
        } else {
            throw new BusinessSmthException("Operation is not supported for user role");
        }
    }

    private Predicate<User> isCompanyNameAcceptable(String newUserCompanyName) {
        return u -> u.getCompany().getName().equalsIgnoreCase(newUserCompanyName);
    }

    private boolean isRoleAcceptable(UserRole newUserRole) {
        return UserRole.COMPANY_EMPLOYER == newUserRole;
    }
}
