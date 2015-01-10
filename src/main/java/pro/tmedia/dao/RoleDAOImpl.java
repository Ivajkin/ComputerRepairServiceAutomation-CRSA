package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Role;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29.06.14
 */
@Transactional
@Repository
public class RoleDAOImpl extends WithSessionFactory implements RoleDAO {

    @Override
    public List<Role> list() {
        return getCurrentSession().createQuery("from Role").list();
    }


}
