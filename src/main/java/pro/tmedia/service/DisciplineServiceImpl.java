package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.DisciplineDAO;
import pro.tmedia.model.Discipline;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class DisciplineServiceImpl implements DisciplineService {

    @Autowired
    private DisciplineDAO disciplineDAO;

    @Override
    public void create(Discipline discipline) {
        disciplineDAO.create(discipline);
    }

    @Override
    public Discipline find(int id) {
        return disciplineDAO.find(id);
    }

    @Override
    public void delete(int id) {
        disciplineDAO.delete(id);
    }

    @Override
    public List<Discipline> findItems() {
        return disciplineDAO.findItems();
    }

    @Override
    public void update(Discipline dictionaryItem) {
        disciplineDAO.update(dictionaryItem);
    }
}