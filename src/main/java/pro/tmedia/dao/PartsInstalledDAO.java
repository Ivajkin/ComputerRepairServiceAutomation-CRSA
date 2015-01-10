package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.PartsInstalled;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 05.08.14
 */
@Transactional
public interface PartsInstalledDAO {
    List<PartsInstalled> list(Integer req_num_id);
    public void create(PartsInstalled partsInstalled);
    public void delete(int id) throws Exception;
    public void update(PartsInstalled partsInstalled);
}
