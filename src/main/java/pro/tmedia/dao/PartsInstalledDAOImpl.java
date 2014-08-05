package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.PartsInstalled;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 05.08.14
 */
@Transactional
@Repository
public class PartsInstalledDAOImpl implements PartsInstalledDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<PartsInstalled> list(Integer req_num_id) {
        return getCurrentSession().createQuery(String.format("from PartsInstalled where request_id = %d", req_num_id)).list();
    }

    @Override
    public void create(PartsInstalled partsInstalled) {
        getCurrentSession().save(partsInstalled);
    }

    @Override
    public void delete(int id) throws Exception {
        PartsInstalled partsInstalled = (PartsInstalled) getCurrentSession().get(PartsInstalled.class, id);
        if(partsInstalled != null)
            getCurrentSession().delete(partsInstalled);
        else throw new Exception("Не удалось удалить установленные запчасти под номером " + id);
    }

    @Override
    public void update(PartsInstalled partsInstalled) {
        getCurrentSession().update(partsInstalled);
    }
}
