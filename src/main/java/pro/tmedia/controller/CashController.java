package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Hardware;
import pro.tmedia.service.HardwareService;

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
public class CashController {

    // TODO: реализовать CashService
    @Autowired
    // CashService cashService;

    final Logger logger = LoggerFactory.getLogger(CashController.class);


    @RequestMapping(value = "/cash/income/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerIncome(@RequestParam Integer cash_type_id, @RequestParam Integer amount) {
        // TODO: доделать тело функции
        return "OK";
    }
    @RequestMapping(value = "/cash/outcome/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerOutcome(@RequestParam Integer cash_type_id, @RequestParam Integer amount) {
        // TODO: доделать тело функции
        return "OK";
    }



}