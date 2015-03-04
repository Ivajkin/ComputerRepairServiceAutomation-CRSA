package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Impairment;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */
@Transactional
public interface ImpairmentDAO {
    List<Impairment> list(Integer id);
    public void create(Impairment impairment);
    public void delete(int id) throws Exception;
    public void update(Impairment impairment);
}
