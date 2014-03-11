package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Manufacturer;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Transactional
public interface TeacherDAO extends DictionaryItemDAO<Manufacturer> {
}
