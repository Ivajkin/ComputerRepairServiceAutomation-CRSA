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
    public void income(Integer id, Integer amount) throws Exception {

        CashType item = DAO.get(id);
        Integer saldo = item.getSaldo() + amount;
        item.setSaldo(saldo);
        DAO.update(item);
    }

    @Override
    public void outcome(Integer id, Integer amount) throws Exception {

        CashType item = DAO.get(id);
        item.setSaldo(item.getSaldo() - amount);
        if(item.getSaldo() < 0) throw new Exception("Нельзя провести операцию - отрицательное сальдо");
        else DAO.update(item);
    }
}
