package pro.tmedia.service.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.UserDAO;
import pro.tmedia.dao.cash.CashOperationDAO;
import pro.tmedia.dao.cash.CashTypeDAO;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.cash.CashOperation;
import pro.tmedia.model.cash.CashOperationStatus;
import pro.tmedia.model.cash.CashType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    CashOperationDAO operationsDAO;
    @Autowired
    UserDAO userDAO;

    public CashType get(Integer id) throws Exception {
        return DAO.get(id);
    }



    @Override
    public void income(Integer id, Integer amount, Employee employee) throws Exception {

        CashType item = DAO.get(id);
        Integer saldo = item.getSaldo() + amount;
        item.setSaldo(saldo);
        DAO.update(item);
        operationsDAO.create(new CashOperation(amount, "Приход по кассе " + item.getName(), item, employee));
    }

    @Override
    public void outcome(Integer id, Integer amount, Employee employee) throws Exception {

        CashType item = DAO.get(id);
        Integer saldo = item.getSaldo() - amount;
        if(saldo < 0) {
            throw new Exception("Нельзя провести операцию - сальдо должно быть положительным");
        }
        else {
            item.setSaldo(saldo);
            DAO.update(item);
        }
        operationsDAO.create(new CashOperation(amount, "Расход по кассе " + item.getName(),  item, employee));

    }


    public List<CashOperation> listOperations() {
        return operationsDAO.list();
    }
}
