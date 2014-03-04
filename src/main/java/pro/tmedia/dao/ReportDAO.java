package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Report;
import pro.tmedia.model.ReportEntry;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/19/14
 */
@Transactional
public interface ReportDAO {
    public void create(Report report);

    Report find(final Integer id);

}
