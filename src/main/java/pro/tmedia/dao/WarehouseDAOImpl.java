package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.WarehouseItem;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class WarehouseDAOImpl implements WarehouseDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<WarehouseItem> list() {
        return getCurrentSession().createQuery("from WarehouseItem").list();
    }

    @Override
    public void create(WarehouseItem warehouseItem) {

        warehouseItem.setDummyValues();
        // TODO: убрать значения по умолчанию
        getCurrentSession().save(warehouseItem);
    }

    @Override
    public void delete(String invoice_number) throws Exception {
        WarehouseItem warehouseItem = (WarehouseItem) getCurrentSession().get(WarehouseItem.class, invoice_number);
        if(warehouseItem != null)
            getCurrentSession().delete(warehouseItem);
        else throw new Exception("Элемента склада с номером накладной: \"" + invoice_number + "\" не существует");
    }

    @Override
    public void update(WarehouseItem warehouseItem) {
        getCurrentSession().update(warehouseItem);
    }
}