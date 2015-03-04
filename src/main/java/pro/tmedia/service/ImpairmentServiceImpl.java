package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.ImpairmentDAO;
import pro.tmedia.model.Impairment;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 4/03/15
 */
@Service
@Transactional
public class ImpairmentServiceImpl implements ImpairmentService {
    @Autowired
    ImpairmentDAO impairmentDAO;

    public List<Impairment> list(Integer id) {
        return impairmentDAO.list(id);
    }

    @Override
    public void create(Impairment impairment) throws Exception {
        impairmentDAO.create(impairment);
    }

    @Override
    public void delete(int id) throws Exception {
        impairmentDAO.delete(id);
    }

    @Override
    public void update(Impairment impairment) {
        impairmentDAO.update(impairment);
    }
}
