package pro.tmedia.service;

import pro.tmedia.model.Fault;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.RequestStatus;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public interface DictionaryItemService {

    public List<Fault> listFaults();
    public List<RequestStatus> requestStatusList();
    public List<Hardware> listHardware();
}
