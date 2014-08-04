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
    CategoryDAO categoryDAO;
    @Autowired
    RequestStatusDAO requestStatusDAO;
    @Autowired
    HardwareDAO hardwareDAO;
    @Autowired
    ProviderDAO providerDAO;
    @Autowired
    ManufacturerDAO manufacturerDAO;
    @Autowired
    TaskTypeDAO taskTypeDAO;
    @Autowired
    AppearanceDAO appearanceDAO;
    @Autowired
    CompletenessDAO completenessDAO;
    @Autowired
    SourceDAO sourceDAO;

    @Override
    public List<Fault> listFaults() {
        return faultDAO.list();
    }

    @Override
    public List<Category> listCategories() {
        return categoryDAO.list();
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
    public List<TaskType> listTask() {
        return taskTypeDAO.list();
    }

    @Override
    public void create(Manufacturer manufacturer) {
        manufacturerDAO.create(manufacturer);
    }

    @Override
    public void create(Hardware hardware) {
        hardwareDAO.create(hardware);
    }

    @Override
    public void delete(int id) throws Exception {
        manufacturerDAO.delete(id);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        manufacturerDAO.update(manufacturer);
    }


    @Override
    public List<Appearance> listAppearances() {
        return appearanceDAO.list();
    }

    @Override
    public List<Completeness> listCompleteness() {
        return completenessDAO.list();
    }

    @Override
    public List<Source> listSources() {
        return sourceDAO.list();
    }
}
