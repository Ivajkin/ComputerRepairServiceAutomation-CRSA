package pro.tmedia.service.cash;


import pro.tmedia.model.Employee;
import pro.tmedia.model.cash.CashOperation;
import pro.tmedia.model.cash.CashType;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 21/06/14
 */
public interface CashTypeService {
    public CashType get(Integer id) throws Exception;
    public void income(Integer id, Integer amount, Employee employee) throws Exception;
    public void outcome(Integer id, Integer amount, Employee employee) throws Exception;
    public List<CashOperation> listOperations();
}
