package pro.tmedia.service;

import pro.tmedia.model.WarehouseItem;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
public interface WarehouseService {
    public List<WarehouseItem> list();
    public void create(WarehouseItem warehouseItem);
    public void delete(Integer id) throws Exception;
    public void update(WarehouseItem warehouseItem);

    WarehouseItem getById(Integer id);
}
