package pro.tmedia.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.WarehouseItem;
import pro.tmedia.service.WarehouseService;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    static Gson gson = new Gson();

    @Autowired
    WarehouseService warehouseService;

    final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> listRequests(@RequestParam("jtStartIndex") int startIndex,
                                                @RequestParam("jtPageSize") int pageSize,
                                                @RequestParam("jtSorting") String sorting) {
        jTableResponse<WarehouseItem> response;
        try
        {
            response = new jTableResponse<WarehouseItem>(warehouseService.list(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<WarehouseItem>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> createWarehouseItem(@ModelAttribute WarehouseItem  warehouseItem, BindingResult result) {
        jTableResponse<WarehouseItem> response;
        if (result.hasErrors()) {
            response = new jTableResponse<WarehouseItem>("Form invalid while create: " + getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(gson.toJson(warehouseItem)));
                warehouseService.create(warehouseItem);
                response = new jTableResponse<WarehouseItem>(warehouseItem);
            } catch (Exception e) {
                response = new jTableResponse<WarehouseItem>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }




    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> updateWarehouseItem(@ModelAttribute WarehouseItem  warehouseItem, BindingResult result) {
        jTableResponse<WarehouseItem> response;
        if (result.hasErrors()) {
            response = new jTableResponse<WarehouseItem>("Form invalid while update: " + getBindingErrorMessages(result));
        } else {
            try {
                warehouseService.update(warehouseItem);
                response = new jTableResponse<WarehouseItem>(warehouseItem);
            } catch (Exception e) {
                response = new jTableResponse<WarehouseItem>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


    private String getBindingErrorMessages(BindingResult bindingResult) {
        String errorMessage = "";
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            errorMessage += error.getObjectName() + " - " + error.getDefaultMessage() + "\n<br/>";
        }
        return errorMessage;
    }
}
