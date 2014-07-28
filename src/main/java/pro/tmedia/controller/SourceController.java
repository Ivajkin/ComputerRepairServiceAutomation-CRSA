package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Source;
import pro.tmedia.service.DictionaryItemService;
import pro.tmedia.service.SourceService;
import pro.tmedia.service.UserService;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
@Controller
@RequestMapping(value = "/source")
public class SourceController {

    @Autowired
    DictionaryItemService dictionaryItemService;
    @Autowired
    SourceService sourceService;

    final Logger logger = LoggerFactory.getLogger(SourceController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Source> list(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Source> response;
        try
        {
            response = new jTableResponse<>(dictionaryItemService.listSources(), false);
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
    public jTableResponse<Source> create(@ModelAttribute Source  source, BindingResult result) {
        jTableResponse<Source> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(source)));
                sourceService.create(source);
                response = new jTableResponse<>(source);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Source> delete(@RequestParam Integer id) {
        jTableResponse<Source> response;
        try {
            sourceService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Source> update(@ModelAttribute Source source, BindingResult result) {
        jTableResponse<Source> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                sourceService.update(source);
                response = new jTableResponse<>(source);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

}
