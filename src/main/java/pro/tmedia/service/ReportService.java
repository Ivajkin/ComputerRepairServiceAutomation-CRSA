package pro.tmedia.service;

import pro.tmedia.model.Report;
import pro.tmedia.model.ReportEntry;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public interface ReportService {
    public Report find(final Integer id);
    public void create(Report report);
}
