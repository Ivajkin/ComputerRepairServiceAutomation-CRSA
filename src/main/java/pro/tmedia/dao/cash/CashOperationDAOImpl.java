package pro.tmedia.dao.cash;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.model.Request;
import pro.tmedia.model.cash.CashOperation;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 28.06.14
 */
@Transactional
@Repository
public class CashOperationDAOImpl implements CashOperationDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<CashOperation> list() {
        return getCurrentSession().createQuery("from CashOperation").list();
    }

    @Override
    public void create(CashOperation cashOperation) {
        getCurrentSession().save(cashOperation);
    }


}
