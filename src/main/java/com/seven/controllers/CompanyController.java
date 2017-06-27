package com.seven.controllers;

import com.seven.controllers.model.CompanyForm;
import com.seven.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by ruslankatsyuryna on 6/27/17.
 */
@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public String getCompaniesPage(Map<String, Object> model) {

        model.put("companies", companyService.getAllCompanies());
        return "companies";
    }

    @GetMapping("/companies/addCompany")
    public String addCompanyForm(Map<String, Object> model) {

        model.put("companyForm", new CompanyForm());

        return "newCompany";
    }

    // TODO : can we have companies with duplicated name/email/address ??
    @PostMapping("/companies/addCompany")
    public ModelAndView createNewCompany(@Valid CompanyForm formCompany, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (!bindingResult.hasErrors()) {
            companyService.createCompany(formCompany);

            modelAndView.addObject("successMessage", "New company created");
            modelAndView.addObject("formCompany", new CompanyForm());
        }

        modelAndView.setViewName("newCompany");

        return modelAndView;
    }

}
