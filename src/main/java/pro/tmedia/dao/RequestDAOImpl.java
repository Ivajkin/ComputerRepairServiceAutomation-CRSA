package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
/*@Transactional*/
@Repository
public class RequestDAOImpl implements RequestDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Request> list() {
        return getCurrentSession().createQuery("from Request").list();
    }

    @Override
    public void create(Request request) {
        getCurrentSession().save(request);
    }

    @Override
    public void delete(int id) throws Exception {
        Request request = (Request) getCurrentSession().get(Request.class, id);
        if(request != null)
            getCurrentSession().delete(request);
        else throw new Exception("Не удалось удалить заявку под номером " + id);
    }

    @Override
    public void update(Request request) {
        getCurrentSession().update(request);
    }
}
