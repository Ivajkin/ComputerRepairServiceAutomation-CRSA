package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.DepartmentDAO;
import pro.tmedia.model.Department;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public void create(Department department) {
        departmentDAO.create(department);
    }

    @Override
    public Department find(int id) {
        return departmentDAO.find(id);
    }

    @Override
    public void delete(int id) {
        departmentDAO.delete(id);
    }

    @Override
    public List<Department> findItems() {
        return departmentDAO.findItems();
    }

    @Override
    public void update(Department dictionaryItem) {
        departmentDAO.update(dictionaryItem);
    }
}