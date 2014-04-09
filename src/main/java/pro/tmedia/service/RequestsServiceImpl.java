package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    RequestDAO requestDAO;

    public List<Request> list() {

        /*List<Request> requests = new ArrayList<>();

        Request request = new Request();
        Hardware hardware = new Hardware();
        hardware.setName("NVIDIA GTX 520");
        hardware.setDescription("Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.");
        request.setHardware(hardware);
        request.setPhone("+7-924-123-45-67");
        request.setAmount(5);
        requests.add(request);


        request = new Request();
        hardware = new Hardware();
        hardware.setName("NVIDIA GTX 550");
        hardware.setDescription("Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.");
        request.setHardware(hardware);
        request.setPhone("+7-924-123-45-67");
        request.setAmount(12);
        requests.add(request);


        return requests;*/
        return requestDAO.list();
    }

    @Override
    public void create(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Request request) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
