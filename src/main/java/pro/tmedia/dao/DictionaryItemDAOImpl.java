package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Transactional
public abstract class DictionaryItemDAOImpl<T> implements DictionaryItemDAO<T> {

    private final Class<T> type;

    DictionaryItemDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)t;
        type = (Class)pt.getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T dictionaryItem) {
        T discipline = dictionaryItem;
        getCurrentSession().save(discipline);
    }

    @Override
    public T find(final int id) {
        T dictionaryItem = (T) getCurrentSession().get(type, id);
        return dictionaryItem;
    }

    @Override
    public void delete(final int id) {
        T dictionaryItem = find(id);
        if(dictionaryItem != null)
            getCurrentSession().delete(dictionaryItem);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> list() {
        return getCurrentSession().createQuery("from " + type.getName()).list();
    }

    @Override
    public void update(T dictionaryItem) {
        getCurrentSession().update(dictionaryItem);
    }
}
