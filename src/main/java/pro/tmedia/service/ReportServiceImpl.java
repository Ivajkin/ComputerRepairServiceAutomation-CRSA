package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.ReportDAO;
import pro.tmedia.dao.ReportEntryDAO;
import pro.tmedia.model.Report;
import pro.tmedia.model.ReportEntry;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    @Override
    public void create(Report report) {
        reportDAO.create(report);
    }
    @Override
    public Report find(Integer id) {
        return reportDAO.find(id);
    }

}