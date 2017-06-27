package com.seven;

import com.seven.models.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import sun.tools.jstat.OptionLister;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
public class SecurityUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    public static boolean isAdmin() {
        Optional<String> maybeRole = findLoggedInUserRole();

        if (maybeRole.isPresent()) {
            return UserRole.ADMIN.toString().equalsIgnoreCase(maybeRole.get());
        }

        return false;
    }

    public static boolean isOwner() {
        Optional<String> maybeRole = findLoggedInUserRole();

        if (maybeRole.isPresent()) {
            return UserRole.COMPANY_OWNER.toString().equalsIgnoreCase(maybeRole.get());
        }

        return false;
    }

    private static Optional<String> findLoggedInUserRole() {
        //user has one role in our case
        Optional<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst();

        LOGGER.debug("User with role {} is logedin", role.get());

        return role.map(GrantedAuthority::getAuthority);
    }

}
