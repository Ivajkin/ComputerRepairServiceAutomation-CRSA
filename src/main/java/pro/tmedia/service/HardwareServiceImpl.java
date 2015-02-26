package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.HardwareDAO;
import pro.tmedia.model.Hardware;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Hardware> listByCategory(Integer category_id) {
        List<Hardware> list = hardwareDAO.list();
        List<Hardware> listByCategory = new ArrayList<>();
        for(Hardware e : list) {
            if(Objects.equals(e.getCategory_id(), category_id))
                listByCategory.add(e);
        }
        return listByCategory;
    }

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
