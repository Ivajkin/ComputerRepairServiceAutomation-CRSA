package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.cash.CashType;
import pro.tmedia.service.HardwareService;
import pro.tmedia.service.cash.CashTypeService;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 19/06/14
3.5	Касса
 -Тип кассы
 -Приход
 -Расход
    3.5.1.При нажатии на кассу, появляется форма, на которой можно выбрать тип кассы по: Безналичные, наличные, карта. При выборе типа, отображается сумма, которая лежит в данной кассе.
    3.5.2.Когда выбрали нужную кассу, при нажатии на кнопку Приход,  открывается форма, где необходимо внести сумму, указать сотрудника, и написать пояснение.
        При нажатии на кнопку Внести, сумма вносится в кассу. По кнопке Назад, пользователь возвращается на форму-Кассы.
    3.5.3. Когда выбрали нужную кассу, при нажатии на кнопку Расход,  открывается форма, где необходимо внести сумму, указать сотрудника, и написать пояснение.
 При нажатии на кнопку Изъять, сумма вычитается  из кассы. По кнопке Назад, пользователь возвращается на форму-Кассы.

 */
@Controller
@RequestMapping(value = "/cash")
public class CashController {

    @Autowired
    CashTypeService service;

    final Logger logger = LoggerFactory.getLogger(CashController.class);


    @RequestMapping(value = "/income", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerIncome(@RequestParam Integer cash_type_id, @RequestParam Integer amount) {
        //cash[cash_type_id].setAmount(cash[cash_type_id].getAmount() + amount);
        return "OK";
    }
    @RequestMapping(value = "/outcome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerOutcome(@RequestParam Integer cash_type_id, @RequestParam Integer amount) {
        //cash[cash_type_id].setAmount(cash[cash_type_id].getAmount() - amount);
        return "OK";
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CashType get(@RequestParam Integer cash_type_id) {
        CashType item = null;
        try {
            item = service.get(cash_type_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }


}
