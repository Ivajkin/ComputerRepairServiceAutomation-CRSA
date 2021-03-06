package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Hardware;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
public interface HardwareDAO {
    List<Hardware> list();
    void create(Hardware hardware);
    public void delete(int id) throws Exception;
    public void update(Hardware hardware);
}
