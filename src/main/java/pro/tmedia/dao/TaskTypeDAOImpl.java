package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.TaskType;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29.06.14
 */
@Transactional
@Repository
public class TaskTypeDAOImpl extends WithSessionFactory implements TaskTypeDAO {

    @Override
    public List<TaskType> list() {
        return getCurrentSession().createQuery("from TaskType").list();
    }
}
