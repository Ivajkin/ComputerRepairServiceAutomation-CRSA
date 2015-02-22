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
    @Override
    public void create(Category category) {
        getCurrentSession().save(category);
    }

    @Override
    public void delete(int id) throws Exception {
        Category category = (Category) getCurrentSession().get(Category.class, id);
        if(category != null)
            getCurrentSession().delete(category);
        else throw new Exception("Не удалось удалить тип внешнего вида под номером " + id);
    }

    @Override
    public void update(Category category) {
        getCurrentSession().update(category);
    }
}
