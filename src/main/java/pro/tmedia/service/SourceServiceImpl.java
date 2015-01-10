package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.*;
import pro.tmedia.model.*;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Service
@Transactional
public class SourceServiceImpl implements SourceService {
    @Autowired
    SourceDAO sourceDAO;

    public List<Source> list() {
        return sourceDAO.list();
    }

    @Override
    public void create(Source source) throws Exception {
        sourceDAO.create(source);
    }

    @Override
    public void delete(int id) throws Exception {
        sourceDAO.delete(id);
    }

    @Override
    public void update(Source source) {
        sourceDAO.update(source);
    }

}
