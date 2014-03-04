package pro.tmedia.service;

import pro.tmedia.model.ReportEntry;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public interface ReportEntryService {
    public List<ReportEntry> findItems(final Integer sem_id, final Integer dept_id, final Integer teacher_id);
    public ReportEntry find(final Integer id);
    public void update(ReportEntry reportEntry);
    public void create(ReportEntry reportEntry);


    public void delete(final int id);
}
