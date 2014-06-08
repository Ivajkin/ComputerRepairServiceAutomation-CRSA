package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 07.06.14
 */
@Transactional
@Repository
public class ManufacturerDAOImpl extends WithSessionFactory implements ManufacturerDAO {


    @Override
    public List<Manufacturer> list() {
        return getCurrentSession().createQuery("from Manufacturer").list();
    }

    @Override
    public void create(Manufacturer manufacturer) {
        getCurrentSession().save(manufacturer);
    }

    @Override
    public void delete(int id) throws Exception {
        Manufacturer manufacturer = (Manufacturer) getCurrentSession().get(Manufacturer.class, id);
        if(manufacturer != null)
            getCurrentSession().delete(manufacturer);
        else throw new Exception("Не удалось удалить производителя под номером " + id);
    }

    @Override
    public void update(Manufacturer manufacturer) {
        getCurrentSession().update(manufacturer);
    }
}
