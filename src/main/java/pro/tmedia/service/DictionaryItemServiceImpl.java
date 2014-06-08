package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.*;
import pro.tmedia.model.*;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Service
@Transactional
public class DictionaryItemServiceImpl implements DictionaryItemService {
    @Autowired
    FaultDAO faultDAO;
    @Autowired
    RequestStatusDAO requestStatusDAO;
    @Autowired
    HardwareDAO hardwareDAO;
    @Autowired
    ProviderDAO providerDAO;
    @Autowired
    ManufacturerDAO manufacturerDAO;

    public List<Fault> listFaults() {
        return faultDAO.list();
    }

    @Override
    public List<Manufacturer> listManufacturers() {
        return manufacturerDAO.list();
    }

    @Override
    public List<RequestStatus> requestStatusList() {
        return requestStatusDAO.list();
    }

    @Override
    public List<Provider> providerList() {
        return providerDAO.list();
    }

    @Override
    public List<Hardware> listHardware() {
        return hardwareDAO.list();
    }

    @Override
    public void create(Manufacturer manufacturer) {
        manufacturerDAO.create(manufacturer);
    }

    @Override
    public void delete(int id) throws Exception {
        manufacturerDAO.delete(id);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        manufacturerDAO.update(manufacturer);
    }
}
