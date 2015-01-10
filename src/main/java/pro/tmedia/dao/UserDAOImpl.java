package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Employee;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29.06.14
 */
@Transactional
@Repository
public class UserDAOImpl extends WithSessionFactory implements UserDAO {

    @Override
    public List<Employee> list() {
        return getCurrentSession().createQuery("from Employee").list();
    }

    @Override
    public void create(Employee employee) {
        getCurrentSession().save(employee);
    }


    @Override
    public void update(Employee employee) {
        getCurrentSession().update(employee);
    }


    @Override
    public Employee get(final Integer employee_id) {
        return (Employee) getCurrentSession().get(Employee.class, employee_id);
    }

    @Override
    public Employee get(String login, String password) throws Exception {
        for(Employee employee : list())
            if(employee.getLogin().equals(login))
                return employee;
        throw new Exception("Не существует пользователя с данными логином и паролем!");
    }
}
