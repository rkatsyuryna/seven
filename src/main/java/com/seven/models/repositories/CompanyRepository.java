package com.seven.models.repositories;

import com.seven.models.domainobjects.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ruslankatsyuryna on 6/24/17.
 */
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findCompanyByName(String companyName);
}
