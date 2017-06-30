package com.seven.services;

/**
 * Created by ruslankatsyuryna on 6/28/17.
 */
public interface CurrentUserService {
    boolean isAdminUser();
    boolean isOwnerUser();
    boolean isEmployeeUser();
}
