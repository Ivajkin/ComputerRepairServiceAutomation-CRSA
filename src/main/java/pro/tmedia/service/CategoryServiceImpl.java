package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.AppearanceDAO;
import pro.tmedia.dao.CategoryDAO;
import pro.tmedia.model.Appearance;
import pro.tmedia.model.Category;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        return categoryDAO.list();
    }

    @Override
    public void create(Category category) throws Exception {
        categoryDAO.create(category);
    }

    @Override
    public void delete(int id) throws Exception {
        categoryDAO.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }
}
