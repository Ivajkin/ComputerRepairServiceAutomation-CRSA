package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Source;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
public interface SourceDAO {
    List<Source> list();
    void create(Source source);
    void delete(int id) throws Exception;
    public void update(Source source);
}
