package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Appearance;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Repository
@Transactional
public final class DisciplineDAOImpl extends DictionaryItemDAOImpl<Appearance> implements DisciplineDAO {
     /*
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void createDictionaryItem(DictionaryItem dictionaryItem) {
        Appearance discipline = (Appearance) dictionaryItem;
        getCurrentSession().save(discipline);
    }

    @Override
    public DictionaryItem getDictionaryItem(int id) {
        DictionaryItem dictionaryItem = (DictionaryItem) getCurrentSession().get(Appearance.class, id);
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
        return getCurrentSession().createQuery("from Appearance").list();
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
