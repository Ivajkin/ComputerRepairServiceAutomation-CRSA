package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.*;
import pro.tmedia.model.cash.CashType;
import pro.tmedia.service.DictionaryItemService;
import pro.tmedia.service.HardwareService;
import pro.tmedia.service.UserService;
import pro.tmedia.service.cash.CashTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
@Controller
public class DictionaryItemController {

    @Autowired
    DictionaryItemService dictionaryItemService;
    @Autowired
    CashTypeService cashTypeService;
    @Autowired
    UserService userService;
    @Autowired
    HardwareService hardwareService;


    final Logger logger = LoggerFactory.getLogger(DictionaryItemController.class);

    @RequestMapping(value = "/fault/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Fault> listFaults() {
        return dictionaryItemList(dictionaryItemService.listFaults());
    }

    @RequestMapping(value = "/category/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Category> listCategories() {
        return dictionaryItemList(dictionaryItemService.listCategories());
    }


    @RequestMapping(value = "/manufacturer/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> listManufacturers(@RequestParam("jtStartIndex") int startIndex,
                                                @RequestParam("jtPageSize") int pageSize,
                                                @RequestParam("jtSorting") String sorting) {
        jTableResponse<Manufacturer> response;
        try
        {
            response = new jTableResponse<>(dictionaryItemService.listManufacturers(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }

    @RequestMapping(value = "/manufacturer/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> createRequest(@ModelAttribute Manufacturer  manufacturer, BindingResult result) {
        jTableResponse<Manufacturer> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(manufacturer)));
                dictionaryItemService.create(manufacturer);
                response = new jTableResponse<>(manufacturer);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/manufacturer/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> deleteManufacturer(@RequestParam Integer id) {
        jTableResponse<Manufacturer> response;
        try {
            dictionaryItemService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/manufacturer/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> updateRequest(@ModelAttribute Manufacturer  manufacturer, BindingResult result) {
        jTableResponse<Manufacturer> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                dictionaryItemService.update(manufacturer);
                response = new jTableResponse<>(manufacturer);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


    @RequestMapping(value = "/payment/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<CashType> listPayment() {
        try {
            List<CashType> list = new ArrayList<>();
            list.add(cashTypeService.get(1));
            list.add(cashTypeService.get(2));
            list.add(cashTypeService.get(3));
            return dictionaryItemList(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new jTableResponse<>("Нет возможности предоставить список касс и способов оплаты");
        }
    }

    @RequestMapping(value = "/request_status/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<RequestStatus> requestStatusList() {
        return dictionaryItemList(dictionaryItemService.requestStatusList());
    }



    @RequestMapping(value = "/provider/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Provider> providerList() {
        return dictionaryItemList(dictionaryItemService.providerList());
    }

    @RequestMapping(value = "/user/list-roles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Role> listRoles() {
        return dictionaryItemList(userService.listRoles());
    }

    @RequestMapping(value = "/employee/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Employee> listEmployee() {
        return dictionaryItemList(userService.list());
    }

    @RequestMapping(value = "/task_type/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<TaskType> listTaskType() {
        return dictionaryItemList(dictionaryItemService.listTaskTypes());
    }

    @RequestMapping(value = "/hardware/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> listHardware() {
        return dictionaryItemList(hardwareService.list());
    }
    @RequestMapping(value = "/manufacturer/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> listManufacturers() {
        return dictionaryItemList(dictionaryItemService.listManufacturers());
    }

    @RequestMapping(value = "/appearance/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Appearance> listAppearance() {
        return dictionaryItemList(dictionaryItemService.listAppearances());
    }
    @RequestMapping(value = "/completeness/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Completeness> listCompleteness() {
        return dictionaryItemList(dictionaryItemService.listCompleteness());
    }
    @RequestMapping(value = "/source/options", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Source> listSource() {
        return dictionaryItemList(dictionaryItemService.listSources());
    }

    private <T> jTableResponse<T> dictionaryItemList(List<T> itemsList) {
        jTableResponse<T> request_status;
        try
        {
            request_status = new jTableResponse<>(itemsList, true);
        }
        catch (Exception ex)
        {
            request_status = new jTableResponse<>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(request_status.getJSON());
        return request_status;
    }

}
