package pro.tmedia.dao.cash;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.cash.CashType;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
public interface CashTypeDAO {
    CashType get(Integer id) throws Exception;
    public void update(CashType cashType);
}
