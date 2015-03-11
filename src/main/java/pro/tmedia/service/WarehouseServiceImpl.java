package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.WarehouseDAO;
import pro.tmedia.model.WarehouseItem;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private Validator validator;

    @Autowired
    WarehouseDAO warehouseDAO;

    public List<WarehouseItem> list() {
        return warehouseDAO.list();
    }

    @Override
    public void create(WarehouseItem warehouseItem) {
        warehouseDAO.create(warehouseItem);
    }

    @Override
    public void delete(Integer id) throws Exception {
        warehouseDAO.delete(id);
    }

    @Override
    public void update(WarehouseItem warehouseItem) {
        warehouseDAO.update(warehouseItem);
    }

    @Override
    public WarehouseItem getById(Integer id) {
        for(WarehouseItem w : list()) {
            if(w.getId() == id)
                return w;
        }
        return null;
    }
}
