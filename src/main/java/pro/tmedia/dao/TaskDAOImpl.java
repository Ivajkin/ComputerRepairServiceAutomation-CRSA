package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Request;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */
@Transactional
@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Task> list(Integer req_num_id) {
        return getCurrentSession().createQuery(String.format("from Task where request_id = %d", req_num_id)).list();
    }

    @Override
    public void create(Task task) {
        getCurrentSession().save(task);
    }

    @Override
    public void delete(int id) throws Exception {
        Task task = (Task) getCurrentSession().get(Task.class, id);
        if(task != null)
            getCurrentSession().delete(task);
        else throw new Exception("Не удалось удалить задачу под номером " + id);
    }

    @Override
    public void update(Task task) {
        getCurrentSession().update(task);
    }
}
