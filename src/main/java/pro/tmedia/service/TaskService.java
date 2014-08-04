package pro.tmedia.service;

import pro.tmedia.model.Request;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */
public interface TaskService {
    public List<Task> list();
    public void create(Task task) throws Exception;
    public void delete(int id) throws Exception;
    public void update(Task task);
}
