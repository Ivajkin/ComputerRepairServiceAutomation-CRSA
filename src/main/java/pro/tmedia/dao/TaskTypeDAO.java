package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.TaskType;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 05.07.14
 */
@Transactional
public interface TaskTypeDAO {

    public List<TaskType> list();
}
