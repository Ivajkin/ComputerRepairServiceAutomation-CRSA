package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import pro.tmedia.dao.RequestDAO;
import pro.tmedia.init.ServiceLayerException;
import pro.tmedia.model.Request;

import javax.annotation.PostConstruct;
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

    public static final int MAX_SLOT_ID = 60;
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

        fillSlot(request.getSlot_id());

        requestDAO.create(request);
    }

    @Override
    public void delete(int id) throws Exception {
        requestDAO.delete(id);
    }

    @Override
    public void update(Request request) {
        if(request.getRequest_status_id() == 3 /* статус Выдан/выполнен */) {

            Date today = Calendar.getInstance().getTime();
            java.sql.Date date = new java.sql.Date(today.getTime());

            request.setDate_of_issue(date);

            Request old_request = requestDAO.get(request.getReq_num_id());
            int old_slot_id = old_request.getSlot_id();
            if(old_slot_id != request.getSlot_id()) {
                emptySlot(old_slot_id);
                fillSlot(request.getSlot_id());
            }
            requestDAO.update(request);
        }
    }

    // Для ремонтируемого оборудования номера ячеек должны лежать в диапазоне от 21 до 60
    @Override
    public int getEmptySlotId() throws Exception {
        int random_slot_id = (int) Math.round(Math.random() * 1000) % MAX_SLOT_ID + 1;
        random_slot_id = placeInRange(random_slot_id, 21, MAX_SLOT_ID);
        for(int i = 0; i < MAX_SLOT_ID; ++i) {
            if(isSlotEmpty( placeInRange((random_slot_id+i)% MAX_SLOT_ID +1, 21, MAX_SLOT_ID))) {
                return random_slot_id;
            }
        }
        throw new Exception("Нет свободных слотов");
    }
    // Для преобразования любого неотрицательного целого числа на лежащее в диапазоне от start до end
    private int placeInRange(int value, int start, int end) {
        if(start > end)
            throw new IllegalArgumentException();
        return value%(end-start+1)+start;
    }

    // Пустые и занятые слоты: инициализируем при запуске из базы, затем дозаполняем по мере изменения слотов (добавление, изменение, удаление)
    private boolean[] is_slot_empty = new boolean[MAX_SLOT_ID+1];

    @PostConstruct
    private void initSlots() {
        // Загружаем данные о заявках из базы, заполняем все пустые ячейки true, заполененные false
        for (int i = 0; i < is_slot_empty.length; i++) {
            emptySlot(i);
        }
        List<Request> requests = requestDAO.list();
        for (Request request : requests) {
            fillSlot(request.getSlot_id());
        }
    }
    private void fillSlot(int slot_id) {
        is_slot_empty[slot_id] = false;
    }
    private void emptySlot(int slot_id) {
        is_slot_empty[slot_id] = true;
    }
    private boolean isSlotEmpty(int slot_id) {
        return is_slot_empty[slot_id];
    }
}
