package com.seven.security;

import com.seven.models.domainobjects.User;
import com.seven.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> maybeUser = userService.getUserByEmail(s);

        if (!maybeUser.isPresent()) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
        User user = maybeUser.get();

        LOGGER.debug("Trying to login for user {}", user);

        return new CurrentUser(user);
    }

    public class CurrentUser extends org.springframework.security.core.userdetails.User {

        private User user;

        public CurrentUser(User user) {
            super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().getRoleName().toString()));
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public Long getId() {
            return user.getId();
        }
    }

}
