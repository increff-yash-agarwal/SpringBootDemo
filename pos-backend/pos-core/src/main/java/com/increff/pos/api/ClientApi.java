package com.increff.pos.api;

import com.increff.pos.Helper.ClientData;
import com.increff.pos.Helper.ClientForm;
import com.increff.pos.Helper.ClientHelper;
import com.increff.pos.dao.ClientDao;
import com.increff.pos.db.ClientPojo;
import com.increff.pos.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientApi {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientDto clientDto;

    /**
     * Handles the transactional flow to establish a brand-new Client Master row.
     */
    @Transactional(rollbackFor = Exception.class)
    public ClientData createClient(ClientForm form) {
        ClientHelper.normalize(form);
        Optional<ClientPojo> existing = clientDao.findByName(form.getName());
        clientDto.validateUniqueClient(existing);

        ClientPojo pojo = ClientHelper.toPojo(form);
        return ClientHelper.toData(clientDao.save(pojo));
    }

    /**
     * Handles the transactional modification flow for an active Client profile.
     */
    @Transactional(rollbackFor = Exception.class)
    public ClientData updateClient(Integer id, ClientForm form) {
        ClientHelper.normalize(form);
        ClientPojo pojo = clientDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + id));

        Optional<ClientPojo> existingName = clientDao.findByName(form.getName());
        clientDto.validateUpdateClient(existingName, id);

        pojo.setName(form.getName());
        return ClientHelper.toData(clientDao.save(pojo));
    }

    /**
     * Fetches a single Client Master identity from storage mapped to presentation
     * Data.
     */
    @Transactional(readOnly = true)
    public ClientData getClient(Integer id) {
        ClientPojo pojo = clientDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + id));
        return ClientHelper.toData(pojo);
    }

    /**
     * Aggregates all registered clients inside a scannable structure array list.
     */
    @Transactional(readOnly = true)
    public List<ClientData> getAllClients() {
        List<ClientPojo> pojos = clientDao.findAll();
        List<ClientData> targetList = new ArrayList<>();
        for (ClientPojo pojo : pojos) {
            targetList.add(ClientHelper.toData(pojo));
        }
        return targetList;
    }
}