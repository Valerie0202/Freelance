package org.perscholas.freelance.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


public class UsernameUniqueImpl implements ConstraintValidator<UsernameUnique, String> {

    @Autowired
    private UserDAO userDao;


    @Override
    public void initialize(UsernameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if ( StringUtils.isEmpty(value) ) {
            return true;
        }
        User user = userDao.findByUsername(value);
        return ( user == null );
    }

}