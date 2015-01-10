package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Completeness;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
public interface CompletenessDAO {
    List<Completeness> list();
    void create(Completeness completeness);
    void delete(int id) throws Exception;
    public void update(Completeness completeness);
}
