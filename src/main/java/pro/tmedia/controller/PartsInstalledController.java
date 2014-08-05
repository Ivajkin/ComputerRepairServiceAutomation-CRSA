package pro.tmedia.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.PartsInstalled;
import pro.tmedia.model.Task;
import pro.tmedia.service.PartsInstalledService;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */
@Controller
@RequestMapping("/parts_installed")
public class PartsInstalledController {

    static final private Gson gson = new Gson();

    @Autowired
    PartsInstalledService partsInstalledService;

    final private Logger logger = LoggerFactory.getLogger(PartsInstalledController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<PartsInstalled> listTasks(@RequestParam Integer req_num_id) {
        jTableResponse<PartsInstalled> response;
        try
        {
            response = new jTableResponse<>(partsInstalledService.list(req_num_id), false);
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
    public jTableResponse<PartsInstalled> createRequest(@ModelAttribute PartsInstalled  partsInstalled, BindingResult result) {
        jTableResponse<PartsInstalled> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(gson.toJson(partsInstalled)));
                partsInstalledService.create(partsInstalled);
                response = new jTableResponse<>(partsInstalled);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }




    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<PartsInstalled> updateRequest(@ModelAttribute PartsInstalled  partsInstalled, BindingResult result) {
        jTableResponse<PartsInstalled> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                partsInstalledService.update(partsInstalled);
                response = new jTableResponse<>(partsInstalled);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<PartsInstalled> deleteWarehouseItem(@RequestParam Integer id) {
        jTableResponse<PartsInstalled> response;
        try {
            partsInstalledService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


}
