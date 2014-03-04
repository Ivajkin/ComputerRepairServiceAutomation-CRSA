package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Department;
import pro.tmedia.model.Discipline;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Transactional
public interface DepartmentDAO extends DictionaryItemDAO<Department> {
}
