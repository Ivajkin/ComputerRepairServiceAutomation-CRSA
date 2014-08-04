package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Request;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */
@Transactional
public interface TaskDAO {
    List<Task> list(Integer req_num_id);
    public void create(Task task);
    public void delete(int id) throws Exception;
    public void update(Task task);
}
