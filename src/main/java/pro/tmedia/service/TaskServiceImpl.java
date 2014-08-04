package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.dao.TaskDAO;
import pro.tmedia.model.Request;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDAO taskDAO;

    public List<Task> list(Integer req_num_id) {
        return taskDAO.list(req_num_id);
    }

    @Override
    public void create(Task task) throws Exception {
        taskDAO.create(task);
    }

    @Override
    public void delete(int id) throws Exception {
        taskDAO.delete(id);
    }

    @Override
    public void update(Task task) {
        taskDAO.update(task);
    }
}
