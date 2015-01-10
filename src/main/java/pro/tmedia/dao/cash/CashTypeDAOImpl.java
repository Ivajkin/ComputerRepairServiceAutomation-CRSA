package pro.tmedia.dao.cash;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.WithSessionFactory;
import pro.tmedia.model.cash.CashType;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class CashTypeDAOImpl extends WithSessionFactory implements CashTypeDAO {

    @Override
    public CashType get(Integer id) throws Exception {
        CashType item = (CashType) getCurrentSession().get(CashType.class, id);
        if(item != null)
            return item;
        else throw new Exception("Не существует кассы с заданным идентификатором: " + id);
    }
    @Override
    public void update(CashType cashType) {
        getCurrentSession().update(cashType);
    }
}
