package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Category;
import pro.tmedia.model.Fault;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 08.06.14
 */
@Transactional
public interface CategoryDAO {
    List<Category> list();
}
