package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.RoleDAO;
import pro.tmedia.dao.UserDAO;
import pro.tmedia.model.Employee;
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

    @Override
    public Employee getCurrentSessionUser() throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        String password = user.getPassword();
        return userDAO.get(login, password);
    }
}
