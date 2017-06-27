package com.seven.controllers.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@Getter
@Setter
public class CompanyForm {

    @NotEmpty(message = "*Please provide company name")
    private String name;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotEmpty(message = "*Please provide company address")
    private String address;

}

