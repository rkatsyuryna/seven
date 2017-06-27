package com.seven.services;

import com.seven.controllers.model.CompanyForm;
import com.seven.models.domainobjects.Company;

import java.util.Collection;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
public interface CompanyService {

    Collection<Company> getAllCompanies();

    Company createCompany(CompanyForm companyForm);
}
