package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.HardwareDAO;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 8/06/14
 */
@Service
@Transactional
public class HardwareServiceImpl implements HardwareService {

    @Autowired
    private Validator validator;

    @Autowired
    HardwareDAO hardwareDAO;

    public List<Hardware> list() {
        return hardwareDAO.list();
    }

    @Override
    public void create(Hardware hardware) {
        hardwareDAO.create(hardware);
    }

    @Override
    public void delete(int id) throws Exception {
        hardwareDAO.delete(id);
    }

    @Override
    public void update(Hardware hardware) {
        hardwareDAO.update(hardware);
    }
}
