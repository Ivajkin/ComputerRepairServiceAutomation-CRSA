package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Department;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Repository
@Transactional
public final class DepartmentDAOImpl extends DictionaryItemDAOImpl<Department> implements DepartmentDAO {
}
