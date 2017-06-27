package com.seven.models.repositories;

import com.seven.models.UserRole;
import com.seven.models.domainobjects.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role getRoleByRoleName(UserRole userRole);
}
