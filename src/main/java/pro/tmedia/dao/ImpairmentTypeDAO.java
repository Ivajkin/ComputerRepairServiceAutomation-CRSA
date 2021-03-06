package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.ImpairmentType;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */
@Transactional
public interface ImpairmentTypeDAO {

    public List<ImpairmentType> list();
}
