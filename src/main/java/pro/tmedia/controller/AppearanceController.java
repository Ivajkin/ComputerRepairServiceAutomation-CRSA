package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Appearance;
import pro.tmedia.model.Source;
import pro.tmedia.service.AppearanceService;
import pro.tmedia.service.DictionaryItemService;
import pro.tmedia.service.SourceService;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
@Controller
@RequestMapping(value = "/appearance")
public class AppearanceController {

    @Autowired
    DictionaryItemService dictionaryItemService;
    @Autowired
    AppearanceService appearanceService;

    final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Appearance> list(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Appearance> response;
        try
        {
            response = new jTableResponse<>(dictionaryItemService.listAppearances(), false);
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
    public jTableResponse<Appearance> create(@ModelAttribute Appearance  appearance, BindingResult result) {
        jTableResponse<Appearance> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(appearance)));
                appearanceService.create(appearance);
                response = new jTableResponse<>(appearance);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Appearance> delete(@RequestParam Integer id) {
        jTableResponse<Appearance> response;
        try {
            appearanceService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Appearance> update(@ModelAttribute Appearance appearance, BindingResult result) {
        jTableResponse<Appearance> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                appearanceService.update(appearance);
                response = new jTableResponse<>(appearance);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

}
