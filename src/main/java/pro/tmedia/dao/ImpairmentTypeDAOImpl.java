package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.ImpairmentType;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */
@Transactional
@Repository
public class ImpairmentTypeDAOImpl extends WithSessionFactory implements ImpairmentTypeDAO {

    @Override
    public List<ImpairmentType> list() {
        return getCurrentSession().createQuery("from ImpairmentType").list();
    }
}
