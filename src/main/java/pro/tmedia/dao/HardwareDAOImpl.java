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
}
