package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.FaultDAO;
import pro.tmedia.dao.HardwareDAO;
import pro.tmedia.model.Fault;
import pro.tmedia.model.Hardware;

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
    HardwareDAO hardwareDAO;

    public List<Fault> listFaults() {
        return faultDAO.list();
    }

    @Override
    public List<Hardware> listHardware() {
        return hardwareDAO.list();
    }
}
