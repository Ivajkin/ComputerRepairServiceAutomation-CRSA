package pro.tmedia.service;

import pro.tmedia.model.Appearance;
import pro.tmedia.model.Category;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
public interface CategoryService {
    public List<Category> list();
    public void create(Category category) throws Exception;
    public void update(Category category);
    public void delete(int id) throws Exception;
}
