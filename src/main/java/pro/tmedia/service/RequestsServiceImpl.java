package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.model.Request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            /*  Номер заявки
                    Формат номера (YMM-#), где
                        Y-последняя цифра года,
                        MM – текущий месяц,
                        # - трехзначный номер заявки)
            */
            DateFormat df = new SimpleDateFormat("yMM");
            Date today = Calendar.getInstance().getTime();
            String YMM = df.format(today);
            YMM = YMM.substring(3);
            int id = request.getRequest_status_id()%999;
            YMM += "-";
            if(id < 100) {
                YMM += "0";
                if(id < 10) {
                    YMM += "0";
                }
            }
            YMM += id;
            request.setRequest_number(YMM);
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
        if(request.getRequest_status_id() == 3 /* статус Выдан/выполнен */) {

            DateFormat df = new SimpleDateFormat("Y-MM-DD");
            Date today = Calendar.getInstance().getTime();
            String todayFormatted = df.format(today);

            request.setDate_of_issue(todayFormatted);
            requestDAO.update(request);
        }
    }
}
