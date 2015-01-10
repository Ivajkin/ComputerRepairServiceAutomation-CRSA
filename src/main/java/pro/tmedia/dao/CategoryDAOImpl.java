package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Category;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 08.06.14
 */
@Transactional
@Repository
public class CategoryDAOImpl extends WithSessionFactory implements CategoryDAO {


    @Override
    public List<Category> list() {
        return getCurrentSession().createQuery("from Category").list();
    }
}
