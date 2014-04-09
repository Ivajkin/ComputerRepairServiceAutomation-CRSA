package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.tmedia.dao.FaultDAO;
import pro.tmedia.model.Fault;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Service
public class DictionaryItemServiceImpl implements DictionaryItemService {
    @Autowired
    FaultDAO faultDAO;

    public List<Fault> listFaults() {
        return faultDAO.list();
    }
}
