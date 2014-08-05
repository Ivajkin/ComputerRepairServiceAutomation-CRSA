package pro.tmedia.service;

import pro.tmedia.model.PartsInstalled;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 05.08.14
 */
public interface PartsInstalledService {
    public List<PartsInstalled> list(Integer req_num_id);
    public void create(PartsInstalled partsInstalled) throws Exception;
    public void delete(int id) throws Exception;
    public void update(PartsInstalled partsInstalled);
}
