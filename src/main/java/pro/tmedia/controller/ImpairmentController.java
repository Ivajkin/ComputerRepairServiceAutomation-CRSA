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
import pro.tmedia.model.Impairment;
import pro.tmedia.service.ImpairmentService;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * User: Ivaykin Timofey
 * Date: 04.08.14
 */
@Controller
@RequestMapping("/impairment")
public class ImpairmentController {

    static Gson gson = new GsonBuilder()
            .setDateFormat("dd.MM.yyyy").create();

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

    @Autowired
    ImpairmentService impairmentService;

    final private Logger logger = LoggerFactory.getLogger(ImpairmentController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Impairment> listImpairments(@RequestParam Integer id) {
        jTableResponse<Impairment> response;
        try
        {
            response = new jTableResponse<>(impairmentService.list(id), false);
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
    public jTableResponse<Impairment> createRequest(@ModelAttribute Impairment  impairment, BindingResult result) {
        jTableResponse<Impairment> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(gson.toJson(impairment)));
                impairmentService.create(impairment);
                response = new jTableResponse<>(impairment);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }




    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Impairment> updateRequest(@ModelAttribute Impairment  impairment, BindingResult result) {
        jTableResponse<Impairment> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                impairmentService.update(impairment);
                response = new jTableResponse<>(impairment);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Impairment> deleteImpairment(@RequestParam Integer id) {
        jTableResponse<Impairment> response;
        try {
            impairmentService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


}
