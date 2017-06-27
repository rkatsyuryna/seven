package com.seven.models.repositories;

import com.seven.models.domainobjects.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    Collection<User> findUsersByCompany_Name(String companyName);
}
