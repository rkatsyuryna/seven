package com.seven.services.impl;

import com.seven.controllers.model.CompanyForm;
import com.seven.models.UserRole;
import com.seven.models.domainobjects.Company;
import com.seven.models.domainobjects.Role;
import com.seven.models.domainobjects.User;
import com.seven.models.repositories.CompanyRepository;
import com.seven.models.repositories.RoleRepository;
import com.seven.models.repositories.UserRepository;
import com.seven.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationServiceImpl notificationService;

    @Override
    public Collection<Company> getAllCompanies() {
        return (Collection<Company>) companyRepository.findAll();
    }

    @Override
    @Transactional
    public Company createCompany(CompanyForm companyForm) {
        Role role = roleRepository.getRoleByRoleName(UserRole.COMPANY_EMPLOYER);

        Company company = new Company(companyForm.getName(), companyForm.getEmail(), companyForm.getAddress());
        companyRepository.save(company);

        // TODO : should be refactored

        String randomPassword = UUID.randomUUID().toString();
        String userMail = "user1@" + companyForm.getName() + ".com";
        User user = User.builder()
                .firstName("Test")
                .lastName("Me")
                .email(userMail)
                .password(passwordEncoder.encode(randomPassword))
                .role(role)
                .company(company).build();
        userRepository.save(user);


        notificationService.sendNotificaitoin(userMail, randomPassword);

        return company;
    }

}
