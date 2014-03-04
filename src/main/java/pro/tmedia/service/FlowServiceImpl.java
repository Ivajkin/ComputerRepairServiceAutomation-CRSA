package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.FlowDAO;
import pro.tmedia.model.Flow;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Service
@Transactional
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowDAO flowDAO;

    @Override
    public void create(Flow flow) {
        flowDAO.create(flow);
    }

    @Override
    public Flow find(int id) {
        return flowDAO.find(id);
    }

    @Override
    public void delete(int id) {
        flowDAO.delete(id);
    }

    @Override
    public List<Flow> findItems() {
        return flowDAO.findItems();
    }

    @Override
    public void update(Flow dictionaryItem) {
        flowDAO.update(dictionaryItem);
    }
}