package com.increff.pos.Helper;

import com.increff.pos.db.UserPojo;

public class UserHelper {

    /**
     * Normalizes incoming raw strings to lower-case and trims whitespaces
     */
    public static void convertToLowerCaseAndTrim(UserForm form) {
        if (form == null || form.getEmail() == null) {
            return;
        }
        form.setEmail(form.getEmail().trim().toLowerCase());
    }

    /**
     * Maps clean Database entity attributes back out to presentation Data layers
     */
    public static DataUser transformPojoToData(UserPojo pojo) {
        if (pojo == null) {
            return null;
        }
        DataUser data = new DataUser();
        data.setId(pojo.getId());
        data.setEmail(pojo.getEmail());
        data.setRole(pojo.getRole());
        return data;
    }
}