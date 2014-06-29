package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.HardwareDAO;
import pro.tmedia.dao.RoleDAO;
import pro.tmedia.dao.UserDAO;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Role;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 29/06/14
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public List<Employee> list() {
        return userDAO.list();
    }

    @Override
    public void create(Employee employee) {
        userDAO.create(employee);
    }


    @Override
    public void update(Employee employee) {
        userDAO.update(employee);
    }

    @Override
    public List<Role> listRoles() {
        return roleDAO.list();
    }
}
