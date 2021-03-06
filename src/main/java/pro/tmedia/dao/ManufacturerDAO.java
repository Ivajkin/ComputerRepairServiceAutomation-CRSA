package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 07.06.14
 */
@Transactional
public interface ManufacturerDAO {
    List<Manufacturer> list();
    void create(Manufacturer manufacturer);
    void delete(int id) throws Exception;
    public void update(Manufacturer manufacturer);
}
