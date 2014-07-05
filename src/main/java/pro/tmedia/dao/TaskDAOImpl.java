package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Task;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29.06.14
 */
@Transactional
@Repository
public class TaskDAOImpl extends WithSessionFactory implements TaskDAO {

    @Override
    public List<Task> list() {
        return getCurrentSession().createQuery("from Task").list();
    }
}
