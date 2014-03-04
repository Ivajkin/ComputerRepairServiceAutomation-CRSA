package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Discipline;
import pro.tmedia.model.Teacher;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Transactional
public interface TeacherDAO extends DictionaryItemDAO<Teacher> {
}
