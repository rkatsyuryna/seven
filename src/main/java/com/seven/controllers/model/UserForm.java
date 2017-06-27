package com.seven.controllers.model;

import com.seven.models.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Getter
@Setter
public class UserForm {

    private String firstName;

    private String lastName;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    private String phone;

    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    private String company;

    private UserRole role = UserRole.COMPANY_EMPLOYER;

}
