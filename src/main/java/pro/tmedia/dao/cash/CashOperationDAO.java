package pro.tmedia.dao.cash;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Request;
import pro.tmedia.model.cash.CashOperation;
import pro.tmedia.model.cash.CashType;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 28.06.14
 */
@Transactional
public interface CashOperationDAO {
    List<CashOperation> list();
    public void create(CashOperation cashOperation);
}
