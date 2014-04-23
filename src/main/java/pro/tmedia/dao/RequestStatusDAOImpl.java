package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.RequestStatus;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
@Repository
public class RequestStatusDAOImpl extends WithSessionFactory implements RequestStatusDAO {


    @Override
    public List<RequestStatus> list() {
        return getCurrentSession().createQuery("from RequestStatus").list();
    }
}
