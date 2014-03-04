package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.TeacherDAO;
import pro.tmedia.model.Teacher;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public void create(Teacher teacher) {
        teacherDAO.create(teacher);
    }

    @Override
    public Teacher find(int id) {
        return teacherDAO.find(id);
    }

    @Override
    public void delete(int id) {
        teacherDAO.delete(id);
    }

    @Override
    public List<Teacher> findItems() {
        return teacherDAO.findItems();
    }

    @Override
    public void update(Teacher dictionaryItem) {
        teacherDAO.update(dictionaryItem);
    }
}