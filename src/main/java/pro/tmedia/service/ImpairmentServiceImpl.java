package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.ImpairmentDAO;
import pro.tmedia.model.Impairment;
import pro.tmedia.model.WarehouseItem;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 4/03/15
 */
@Service
@Transactional
public class ImpairmentServiceImpl implements ImpairmentService {
    @Autowired
    ImpairmentDAO impairmentDAO;
    @Autowired
    WarehouseService warehouseService;

    public List<Impairment> list(Integer id) {
        return impairmentDAO.list(id);
    }

    @Override
    public void create(Impairment impairment) throws Exception {
        // Через WarehouseService меняем количество оставшихся товаров
        WarehouseItem warehouse = warehouseService.getById(impairment.getWarehouse_item_id());

        warehouse.setItem_count(warehouse.getItem_count() - impairment.getCount_hardware_impairment());
        warehouseService.update(warehouse);
        impairmentDAO.create(impairment);
    }

    @Override
    public void delete(int id) throws Exception {
        impairmentDAO.delete(id);
    }

    @Override
    public void update(Impairment impairment) {
        impairmentDAO.update(impairment);
    }
}
