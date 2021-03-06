package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Fault;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class FaultDAOImpl extends WithSessionFactory implements FaultDAO {


    @Override
    public List<Fault> list() {
        return getCurrentSession().createQuery("from Fault").list();
    }
}
