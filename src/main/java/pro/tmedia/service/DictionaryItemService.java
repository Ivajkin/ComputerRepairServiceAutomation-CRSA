package pro.tmedia.service;

import pro.tmedia.model.*;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public interface DictionaryItemService {

    public List<Fault> listFaults();
    public List<RequestStatus> requestStatusList();
    public List<Provider> providerList();
    public List<Hardware> listHardware();
    public List<Manufacturer> listManufacturers();
    public void create(Manufacturer manufacturer);
}
