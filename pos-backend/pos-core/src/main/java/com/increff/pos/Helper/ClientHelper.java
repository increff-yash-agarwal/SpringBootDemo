package com.increff.pos.Helper;

import com.increff.pos.db.ClientPojo;

public class ClientHelper {

    /**
     * Normalizes the incoming form data by trimming whitespaces and
     * forcing the text into lowercase.
     */
    public static void normalize(ClientForm form) {
        if (form == null || form.getName() == null) {
            return;
        }
        form.setName(form.getName().trim().toLowerCase());
    }

    /**
     * Transforms a clean Database persistent Pojo into an outbound
     * contract structure for the UI presentation layer.
     */
    public static ClientData toData(ClientPojo pojo) {
        if (pojo == null) {
            return null;
        }
        ClientData data = new ClientData();
        data.setId(pojo.getId());
        data.setName(pojo.getName());
        return data;
    }

    /**
     * Converts an inbound validated Form directly into a new clean
     * Database persistent Pojo structure.
     */
    public static ClientPojo toPojo(ClientForm form) {
        if (form == null) {
            return null;
        }
        ClientPojo pojo = new ClientPojo();
        pojo.setName(form.getName());
        return pojo;
    }
}