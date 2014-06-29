package pro.tmedia.service;

import pro.tmedia.model.Employee;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Role;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29/06/14
 */
public interface UserService {
    public List<Employee> list();
    public void create(Employee employee);
    public void update(Employee employee);
    public List <Role>  listRoles();
}
