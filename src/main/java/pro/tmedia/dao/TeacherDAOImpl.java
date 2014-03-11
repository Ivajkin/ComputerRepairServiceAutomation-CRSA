package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Repository
@Transactional
public final class TeacherDAOImpl extends DictionaryItemDAOImpl<Manufacturer> implements TeacherDAO {
}
