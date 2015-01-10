package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Completeness;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
@Repository
public class CompletenessDAOImpl extends WithSessionFactory implements CompletenessDAO {


    @Override
    public List<Completeness> list() {
        return getCurrentSession().createQuery("from Completeness").list();
    }

    @Override
    public void create(Completeness completeness) {
        getCurrentSession().save(completeness);
    }

    @Override
    public void delete(int id) throws Exception {
        Completeness completeness = (Completeness) getCurrentSession().get(Completeness.class, id);
        if(completeness != null)
            getCurrentSession().delete(completeness);
        else throw new Exception("Не удалось удалить тип комплектности под номером " + id);
    }

    @Override
    public void update(Completeness completeness) {
        getCurrentSession().update(completeness);
    }
}
