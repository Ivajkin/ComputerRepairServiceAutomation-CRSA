package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.SemesterDAO;
import pro.tmedia.model.Semester;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterDAO semesterDAO;

    @Override
    public void create(Semester semester) {
        semesterDAO.create(semester);
    }

    @Override
    public Semester find(int id) {
        return semesterDAO.find(id);
    }

    @Override
    public void delete(int id) {
        semesterDAO.delete(id);
    }

    @Override
    public List<Semester> findItems() {
        return semesterDAO.findItems();
    }

    @Override
    public void update(Semester dictionaryItem) {
        semesterDAO.update(dictionaryItem);
    }
}