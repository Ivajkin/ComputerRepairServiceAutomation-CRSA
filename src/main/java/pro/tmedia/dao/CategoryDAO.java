package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Category;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 08.06.14
 */
@Transactional
public interface CategoryDAO {
    List<Category> list();
    void create(Category category);
    void delete(int id) throws Exception;
    public void update(Category category);
}
