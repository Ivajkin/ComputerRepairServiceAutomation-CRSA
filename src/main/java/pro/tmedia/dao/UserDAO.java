package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Hardware;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29.06.14
 */
@Transactional
public interface UserDAO {
    List<Employee> list();
    void create(Employee employee);
    public void update(Employee employee);
    public Employee get(Integer employee_id);
}
