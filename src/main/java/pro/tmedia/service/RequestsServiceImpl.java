package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Service
@Transactional
public class RequestsServiceImpl implements RequestsService {

    @Autowired
    private Validator validator;

    @Autowired
    RequestDAO requestDAO;

    public List<Request> list() {
        return requestDAO.list();
    }

    @Override
    public void create(Request request) throws Exception {
        if(request.getRequest_number() == null) {
            request.setRequest_number("123-321");
        } else {
            throw new Exception("При создании серийного номера кто-то заранее заполнил его до слоя сервисов.");
        }
        requestDAO.create(request);
    }

    @Override
    public void delete(int id) throws Exception {
        requestDAO.delete(id);
    }

    @Override
    public void update(Request request) {
        requestDAO.update(request);
    }
}
