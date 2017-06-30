package com.seven.services.impl;

import com.seven.SecurityUtils;
import com.seven.services.CurrentUserService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslankatsyuryna on 6/28/17.
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public boolean isAdminUser() {
        return SecurityUtils.isAdmin();
    }

    @Override
    public boolean isOwnerUser() {
        return SecurityUtils.isOwner();
    }

    @Override
    public boolean isEmployeeUser() {
        return false;
    }
}
