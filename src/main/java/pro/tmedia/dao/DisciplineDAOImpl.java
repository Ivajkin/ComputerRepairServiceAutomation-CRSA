package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.DictionaryItem;
import pro.tmedia.model.Discipline;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Repository
@Transactional
public final class DisciplineDAOImpl extends DictionaryItemDAOImpl<Discipline> implements DisciplineDAO {
     /*
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createDictionaryItem(DictionaryItem dictionaryItem) {
        Discipline discipline = (Discipline) dictionaryItem;
        getCurrentSession().save(discipline);
    }

    @Override
    public DictionaryItem getDictionaryItem(int id) {
        DictionaryItem dictionaryItem = (DictionaryItem) getCurrentSession().get(Discipline.class, id);
        return dictionaryItem;
    }

    @Override
    public void deleteDictionaryItem(int id) {
        DictionaryItem dictionaryItem = getDictionaryItem(id);
        if(dictionaryItem != null)
            getCurrentSession().delete(dictionaryItem);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DictionaryItem> getDictionaryItems() {
        return getCurrentSession().createQuery("from Discipline").list();
    }    */

    /*
     *  FOR UPDATE:
     *  public void updateTeam(Team team) {
     *      Team teamToUpdate = getTeam(team.getId());
     *      teamToUpdate.setName(team.getName());
     *      teamToUpdate.setRating(team.getRating());
     *      getCurrentSession().update(teamToUpdate);
     *  }
     */
}
