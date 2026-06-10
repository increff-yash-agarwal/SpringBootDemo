package com.increff.pos.api;

import com.increff.pos.Helper.DataUser;
import com.increff.pos.Helper.UserForm;
import com.increff.pos.Helper.UserHelper;
import com.increff.pos.dao.UserDao;
import com.increff.pos.db.UserPojo;
import com.increff.pos.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserApi {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDto userDto;

    /**
     * Executes the sequential structural pipeline to register an incoming user.
     * Strict 20-line layout rule applied.
     */
    @Transactional(rollbackFor = Exception.class)
    public DataUser createUser(UserForm form) {
        UserHelper.convertToLowerCaseAndTrim(form);

        Optional<UserPojo> existingUser = userDao.findByEmail(form.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with this email address already exists.");
        }

        String assignedRole = userDto.determineRoleByEmail(form.getEmail());

        UserPojo newPojo = new UserPojo();
        newPojo.setEmail(form.getEmail());
        newPojo.setRole(assignedRole);

        UserPojo savedPojo = userDao.save(newPojo);
        return UserHelper.transformPojoToData(savedPojo);
    }
}