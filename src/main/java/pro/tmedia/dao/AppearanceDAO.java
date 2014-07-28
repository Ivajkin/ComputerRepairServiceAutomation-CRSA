package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Appearance;
import pro.tmedia.model.Source;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.14
 */
@Transactional
public interface AppearanceDAO {
    List<Appearance> list();
    void create(Appearance appearance);
    void delete(int id) throws Exception;
    public void update(Appearance appearance);
}
