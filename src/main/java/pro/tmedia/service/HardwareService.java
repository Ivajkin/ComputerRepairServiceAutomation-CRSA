package pro.tmedia.service;

import pro.tmedia.model.Hardware;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
public interface HardwareService {
    public List<Hardware> list();
    public List<Hardware> listByCategory(Integer category_id);
    public void create(Hardware hardware);
    public void delete(int id) throws Exception;
    public void update(Hardware hardware);
}
