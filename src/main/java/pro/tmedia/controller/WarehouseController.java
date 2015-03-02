package pro.tmedia.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.WarehouseItem;
import pro.tmedia.service.WarehouseService;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    static Gson gson = new GsonBuilder()
            .setDateFormat("dd.MM.yyyy").create();

    @Autowired
    WarehouseService warehouseService;

    final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(java.sql.Date.class,
            new PropertyEditorSupport() {
                public void setAsText(String value) {
                    try {
                        java.sql.Date parsedDate = new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(value).getTime());
                        setValue(new Date(parsedDate.getTime()));
                    } catch (ParseException e) {
                        setValue(null);
                    }
                }
            });
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> listRequests(@RequestParam("jtStartIndex") int startIndex,
                                                @RequestParam("jtPageSize") int pageSize,
                                                @RequestParam("jtSorting") String sorting) {
        jTableResponse<WarehouseItem> response;
        try
        {
            List<WarehouseItem> list = warehouseService.list();
            if (list.size() > 0) {
                Comparator<WarehouseItem> sort_comparator;
                switch(sorting) {
                    case "item_count ASC":
                        sort_comparator =  new Comparator<WarehouseItem>() {
                            @Override
                            public int compare(final WarehouseItem object1, final WarehouseItem object2) {
                                return object1.getItem_count().compareTo(object2.getItem_count());
                            }
                        };
                        break;
                    case "item_count DESC":
                        sort_comparator =  new Comparator<WarehouseItem>() {
                            @Override
                            public int compare(final WarehouseItem object1, final WarehouseItem object2) {
                                return -object1.getItem_count().compareTo(object2.getItem_count());
                            }
                        };
                        break;
                    default:
                        sort_comparator =  new Comparator<WarehouseItem>() {
                            @Override
                            public int compare(final WarehouseItem object1, final WarehouseItem object2) {
                                return object1.getInvoice_number().compareTo(object2.getInvoice_number());
                            }
                        };
                }
                Collections.sort(list, sort_comparator);
            }
            response = new jTableResponse<>(list, false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<>(ex.getMessage());
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
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(gson.toJson(warehouseItem)));
                warehouseService.create(warehouseItem);
                response = new jTableResponse<>(warehouseItem);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }




    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> updateWarehouseItem(@ModelAttribute WarehouseItem warehouseItem, BindingResult result) {
        jTableResponse<WarehouseItem> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                warehouseService.update(warehouseItem);
                response = new jTableResponse<>(warehouseItem);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<WarehouseItem> deleteWarehouseItem(@RequestParam Integer id) {
        jTableResponse<WarehouseItem> response;
            try {
                warehouseService.delete(id);
                response = new jTableResponse<>();
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        return response;
    }
}
