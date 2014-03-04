package pro.tmedia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.*;

import java.util.Collections;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/19/14
 */
@Repository
@Transactional
public class ReportEntryDAOImpl implements ReportEntryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(ReportEntry reportEntry) {
        getCurrentSession().save(reportEntry);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReportEntry> findItems(final Integer sem_id, final Integer dept_id, final Integer teacher_id) {

        if(getCurrentSession().createQuery("from Report " +
                "where (sem_id=" + sem_id + ") " +
                "and (dept_id=" + dept_id + ") " +
                "and (teacher_id=" + teacher_id + ")").list().size() == 0) {
            Report report = new Report();
            Semester sem = new Semester(); sem.setId(sem_id);
            Department dept = new Department(); dept.setId(dept_id);
            Teacher teacher = new Teacher(); teacher.setId(teacher_id);
            report.setSem(sem); report.setDept(dept); report.setTeacher(teacher);
            getCurrentSession().save(report);
            return Collections.<ReportEntry>emptyList();
        } else {
            return getCurrentSession().createQuery("select entries " +
                    "from ReportEntry entries, Report report " +
                    "where entries.report.id=report.id").list();
        }
    }

    @Override
    public void update(ReportEntry reportEntry) {
        getCurrentSession().update(reportEntry);
    }

    @Override
    public ReportEntry find(final Integer id) {
        return (ReportEntry)getCurrentSession().get(ReportEntry.class, id);
    }

    @Override
    public void delete(final int id) {
        ReportEntry reportEntry = find(id);
        if(reportEntry != null)
            getCurrentSession().delete(reportEntry);
    }
}
