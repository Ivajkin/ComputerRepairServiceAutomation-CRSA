package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Impairment;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */
@Transactional
@Repository
public class ImpairmentDAOImpl implements ImpairmentDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Impairment> list(Integer id) {
        return getCurrentSession().createQuery(String.format("from Impairment where warehouse_item_id = %d", id)).list();
    }

    @Override
    public void create(Impairment impairment) {
        getCurrentSession().save(impairment);
    }

    @Override
    public void delete(int id) throws Exception {
        Impairment impairment = (Impairment) getCurrentSession().get(Impairment.class, id);
        if(impairment != null)
            getCurrentSession().delete(impairment);
        else throw new Exception("Не удалось списать товар под номером " + id);
    }

    @Override
    public void update(Impairment impairment) {
        getCurrentSession().update(impairment);
    }
}
