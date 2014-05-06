package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.WarehouseItem;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
public interface WarehouseDAO {
    List<WarehouseItem> list();
    public void create(WarehouseItem warehouseItem);
    public void delete(int id);
    public void update(WarehouseItem warehouseItem);
}
