package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Appearance;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
@Repository
public class AppearanceDAOImpl extends WithSessionFactory implements AppearanceDAO {


    @Override
    public List<Appearance> list() {
        return getCurrentSession().createQuery("from Appearance").list();
    }

    @Override
    public void create(Appearance appearance) {
        getCurrentSession().save(appearance);
    }

    @Override
    public void delete(int id) throws Exception {
        Appearance appearance = (Appearance) getCurrentSession().get(Appearance.class, id);
        if(appearance != null)
            getCurrentSession().delete(appearance);
        else throw new Exception("Не удалось удалить тип внешнего вида под номером " + id);
    }

    @Override
    public void update(Appearance appearance) {
        getCurrentSession().update(appearance);
    }
}
