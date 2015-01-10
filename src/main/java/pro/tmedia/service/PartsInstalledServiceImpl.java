package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.PartsInstalledDAO;
import pro.tmedia.model.PartsInstalled;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 05.08.2014
 */
@Service
@Transactional
public class PartsInstalledServiceImpl implements PartsInstalledService {
    @Autowired
    PartsInstalledDAO partsInstalledDAO;

    public List<PartsInstalled> list(Integer req_num_id) {
        return partsInstalledDAO.list(req_num_id);
    }

    @Override
    public void create(PartsInstalled partsInstalled) throws Exception {
        partsInstalledDAO.create(partsInstalled);
    }

    @Override
    public void delete(int id) throws Exception {
        partsInstalledDAO.delete(id);
    }

    @Override
    public void update(PartsInstalled partsInstalled) {
        partsInstalledDAO.update(partsInstalled);
    }
}
