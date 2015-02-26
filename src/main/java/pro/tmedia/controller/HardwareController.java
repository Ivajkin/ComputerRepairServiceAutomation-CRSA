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

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 8/06/14
 */
@Controller
public class HardwareController {

    @Autowired
    HardwareService hardwareService;

    final Logger logger = LoggerFactory.getLogger(HardwareController.class);



    @RequestMapping(value = "/hardware/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> listHardware(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Hardware> response;
        try
        {
            response = new jTableResponse<>(hardwareService.list(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }


    @RequestMapping(value = "/hardware/list/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Hardware> listByCategory(@RequestParam Integer category_id) {
        if(null != category_id)
            return hardwareService.listByCategory(category_id);
        else
            return hardwareService.list();
    }

    @RequestMapping(value = "/hardware/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> createRequest(@ModelAttribute Hardware  hardware, BindingResult result) {
        jTableResponse<Hardware> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(hardware)));
                hardwareService.create(hardware);
                response = new jTableResponse<>(hardware);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/hardware/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> deleteHardware(@RequestParam Integer id) {
        jTableResponse<Hardware> response;
        try {
            hardwareService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/hardware/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Hardware> updateRequest(@ModelAttribute Hardware  hardware, BindingResult result) {
        jTableResponse<Hardware> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                hardwareService.update(hardware);
                response = new jTableResponse<>(hardware);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


}
