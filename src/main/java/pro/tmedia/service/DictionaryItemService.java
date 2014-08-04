package pro.tmedia.service;

import pro.tmedia.model.*;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public interface DictionaryItemService {

    public List<Fault> listFaults();
    public List<Category> listCategories();
    public List<RequestStatus> requestStatusList();
    public List<Provider> providerList();
    public List<TaskType> listTask();
    public void create(Manufacturer manufacturer);
    public void delete(int id) throws Exception;
    public void update(Manufacturer manufacturer);
    public void create(Hardware hardware);

    public List<Manufacturer> listManufacturers();
    public List<Appearance> listAppearances();
    public List<Completeness> listCompleteness();
    public List<Source> listSources();
}
