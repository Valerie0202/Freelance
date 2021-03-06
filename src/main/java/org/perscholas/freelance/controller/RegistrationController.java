package org.perscholas.freelance.controller;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.dao.UserRoleDAO;
import org.perscholas.freelance.database.entity.User;
import org.perscholas.freelance.database.entity.UserRole;
import org.perscholas.freelance.form.RegisterFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            errors.getFieldErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));

            response.addObject("errorMessages", errorMessages);
            response.addObject("form", form);
            response.setViewName("registration/register");

        } else {
            User user;

            if ( form.getId() == null ) {
                user  = new User();
            } else {
                user = userDao.findById(form.getId());
            }

            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setUsername(form.getUsername());
            user.setAddress1(form.getAddress1());
            user.setAddress2(form.getAddress2());
            user.setPhone(form.getPhone());
            user.setPassword(encryptedPassword);
            user = userDao.save(user);

            if ( form.getId() == null ) {
                UserRole ur = new UserRole();

                ur.setUser(user);
                ur.setUserRole("USER");

                userRoleDao.save(ur);
                response.setViewName("login/login");
            } else {
                response.setViewName("registration/register");
            }
        }

        return response;
    }


}
