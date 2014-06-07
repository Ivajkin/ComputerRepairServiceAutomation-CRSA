package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;

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
}
