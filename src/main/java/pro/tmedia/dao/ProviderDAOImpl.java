package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Provider;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class ProviderDAOImpl extends WithSessionFactory implements ProviderDAO {

    @Override
    public List<Provider> list() {
        return getCurrentSession().createQuery("from Provider").list();
    }
}
