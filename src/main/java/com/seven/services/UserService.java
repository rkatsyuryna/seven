package com.seven.services;

import com.seven.controllers.model.UserForm;
import com.seven.exceptions.BusinessSmthException;
import com.seven.models.domainobjects.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsersManagedBy(String userName);

    Collection<User> getAllUsersByCompany(String companyName);

    /**
     *
     * @param userName
     * @param newUser
     * @return
     *
     * @throws BusinessSmthException
     */
    User createManagedUser(String userName, UserForm newUser);
}
