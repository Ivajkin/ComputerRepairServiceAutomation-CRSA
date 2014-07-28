package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;
import pro.tmedia.model.Source;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
@Repository
public class SourceDAOImpl extends WithSessionFactory implements SourceDAO {


    @Override
    public List<Source> list() {
        return getCurrentSession().createQuery("from Source").list();
    }

    @Override
    public void create(Source source) {
        getCurrentSession().save(source);
    }

    @Override
    public void delete(int id) throws Exception {
        Source source = (Source) getCurrentSession().get(Source.class, id);
        if(source != null)
            getCurrentSession().delete(source);
        else throw new Exception("Не удалось удалить источник под номером " + id);
    }

    @Override
    public void update(Source source) {
        getCurrentSession().update(source);
    }
}
