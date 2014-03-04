package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Transactional
public interface DictionaryItemDAO<T> {
    public void create(T dictionaryItem);
    public T find(int id);
    public void delete(int id);
    public List<T> findItems();

    public void update(T dictionaryItem);
}
