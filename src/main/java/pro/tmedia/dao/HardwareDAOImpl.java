package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Hardware;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class HardwareDAOImpl extends WithSessionFactory implements HardwareDAO {

    @Override
    public List<Hardware> list() {
        return getCurrentSession().createQuery("from Hardware").list();
    }

    @Override
    public void create(Hardware hardware) {
        getCurrentSession().save(hardware);
    }

    @Override
    public void delete(int id) throws Exception {
        Hardware hardware = (Hardware) getCurrentSession().get(Hardware.class, id);
        if(hardware != null)
            getCurrentSession().delete(hardware);
        else throw new Exception("Не удалось удалить оборудование под номером " + id);
    }

    @Override
    public void update(Hardware hardware) {
        getCurrentSession().update(hardware);
    }
}
