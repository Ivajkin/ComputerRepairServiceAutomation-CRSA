package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.init.ServiceLayerException;
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

    private static int current_request_index = (int)Math.round(Math.random() * 1000)%999;
    @Override
    public void create(Request request) throws ServiceLayerException {
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
            current_request_index = (++current_request_index)%999;
            YMM += "-";
            if(current_request_index < 100) {
                YMM += "0";
                if(current_request_index < 10) {
                    YMM += "0";
                }
            }
            YMM += current_request_index;
            request.setRequest_number(YMM);
        } else {
            throw new ServiceLayerException("При создании серийного номера кто-то заранее заполнил его до слоя сервисов.");
        }
        if(null == request.getRequest_status_id()) {
            request.setRequest_status_id(1);
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
            //String todayFormatted = df.format(today);
            java.sql.Date date = new java.sql.Date(today.getTime());

            request.setDate_of_issue(date);
            requestDAO.update(request);
        }
    }
}
