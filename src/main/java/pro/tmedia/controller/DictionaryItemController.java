package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.*;
import pro.tmedia.service.DictionaryItemService;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
@Controller
public class DictionaryItemController {

    @Autowired
    DictionaryItemService dictionaryItemService;

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
            response = new jTableResponse<Manufacturer>(dictionaryItemService.listManufacturers(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<Manufacturer>(ex.getMessage());
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
            response = new jTableResponse<Manufacturer>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(manufacturer)));
                dictionaryItemService.create(manufacturer);
                response = new jTableResponse<Manufacturer>(manufacturer);
            } catch (Exception e) {
                response = new jTableResponse<Manufacturer>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/manufacturer/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Manufacturer> deleteWarehouseItem(@RequestParam Integer id) {
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
            response = new jTableResponse<Manufacturer>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                dictionaryItemService.update(manufacturer);
                response = new jTableResponse<Manufacturer>(manufacturer);
            } catch (Exception e) {
                response = new jTableResponse<Manufacturer>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/hardware/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> listHardware(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Hardware> response;
        try
        {
            response = new jTableResponse<Hardware>(dictionaryItemService.listHardware(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<Hardware>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }

    @RequestMapping(value = "/hardware/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> createRequest(@ModelAttribute Hardware  hardware, BindingResult result) {
        jTableResponse<Hardware> response;
        if (result.hasErrors()) {
            response = new jTableResponse<Hardware>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(hardware)));
                dictionaryItemService.create(hardware);
                response = new jTableResponse<Hardware>(hardware);
            } catch (Exception e) {
                response = new jTableResponse<Hardware>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
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

    private <T> jTableResponse<T> dictionaryItemList(List<T> itemsList) {
        jTableResponse<T> request_status;
        try
        {
            request_status = new jTableResponse<T>(itemsList, true);
        }
        catch (Exception ex)
        {
            request_status = new jTableResponse<T>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(request_status.getJSON());
        return request_status;
    }
}
