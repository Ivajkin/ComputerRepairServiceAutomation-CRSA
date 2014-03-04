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
public class ReportDAOImpl implements ReportDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Report report) {
        getCurrentSession().save(report);
    }

    @Override
    public Report find(final Integer id) {
        return (Report)getCurrentSession().get(Report.class, id);
    }
}
