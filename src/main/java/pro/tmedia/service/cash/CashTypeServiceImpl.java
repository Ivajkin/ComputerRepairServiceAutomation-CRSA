package pro.tmedia.service.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.cash.CashTypeDAO;
import pro.tmedia.model.cash.CashType;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 21/06/14
 */
@Service
@Transactional
public class CashTypeServiceImpl implements CashTypeService {

    @Autowired
    CashTypeDAO DAO;

    public CashType get(Integer id) throws Exception {
        return DAO.get(id);
    }

    @Override
    public void update(CashType item) {
        DAO.update(item);
    }
}
