package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.ReportEntry;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/19/14
 */
@Transactional
public interface ReportEntryDAO {
    public List<ReportEntry> findItems(final Integer sem_id, final Integer dept_id, final Integer teacher_id);
    public void create(ReportEntry reportEntry);

    void update(ReportEntry reportEntry);
    ReportEntry find(final Integer id);

    public void delete(final int id);

}
