package com.seven.controllers;

import com.seven.controllers.model.UserForm;
import com.seven.exceptions.BusinessSmthException;
import com.seven.models.domainobjects.User;
import com.seven.services.CompanyService;
import com.seven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/users")
    public String getUsersPage(Map<String, Object> model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        model.put("username", username);

        model.put("users", userService.getAllUsersManagedBy(username));
        return "users";
    }

    @GetMapping("/users/addUser")
    public String addUserForm(Map<String, Object> model) {

        model.put("userForm", new UserForm());

        //need to be added to template
        model.put("companies", companyService.getAllCompanies());

        return "newUser";
    }


    @PostMapping("/users/addUser")
    public ModelAndView createNewUser(@Valid UserForm formUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.getUserByEmail(formUser.getEmail());
        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (!bindingResult.hasErrors()) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName(); //get logged in username

            try {
                userService.createManagedUser(username, formUser);
            } catch (BusinessSmthException e) {
                bindingResult
                        .rejectValue("company", "error.company", e.getMessage());
                modelAndView.setViewName("newUser");

                return modelAndView;
            }

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("userForm", new UserForm());
        }
        modelAndView.setViewName("newUser");

        return modelAndView;
    }

    @PostMapping("/users/updateUser")
    public String updateUser(@ModelAttribute("user") UserForm user) {

        return "users";
    }
}
