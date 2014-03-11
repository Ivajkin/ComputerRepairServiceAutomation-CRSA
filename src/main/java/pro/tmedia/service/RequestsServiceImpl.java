package pro.tmedia.service;

import org.springframework.stereotype.Service;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Service
public class RequestsServiceImpl implements RequestsService {
    public List<Request> findByNameContains(String name) {

        List<Request> requests = new ArrayList<>();

        if(name.contains("NVIDIA GTX 520")) {

            Request request = new Request();
            Hardware hardware = new Hardware();
            hardware.setName("NVIDIA GTX 520");
            hardware.setDescription("Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.");
            request.setHardware(hardware);
            request.setPhone("+7-924-123-45-67");
            requests.add(request);
        }

        return requests;
    }
}
