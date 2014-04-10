package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Request;
import pro.tmedia.service.RequestsService;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping("/requests")
public class RequestsController {
    @Autowired
    RequestsService requestsService;

    final Logger logger = LoggerFactory.getLogger(RequestsController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Request> listRequests(@RequestParam("jtStartIndex") int startIndex,
                                                @RequestParam("jtPageSize") int pageSize,
                                                @RequestParam("jtSorting") String sorting) {
        jTableResponse<Request> response;
        try
        {
            response = new jTableResponse<Request>(requestsService.list());
        }
        catch (Exception ex)
        {
            response = new jTableResponse<Request>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }

    /*@RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createRequest(HttpServletRequest httpRequest,  HttpServletResponse response, @RequestBody Request request) throws IOException {
        String json;
        try
        {
            requestsService.create(request);
            json = new jTableResponse<Request>(request).getJSON();
        }
        catch (Exception ex)
        {
            json = new jTableResponse<Request>(ex.getMessage()).getJSON();
        }

        logger.info(json);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }    */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Request> createRequest(@ModelAttribute Request  request, BindingResult result) {
        jTableResponse<Request> response;
        if (result.hasErrors()) {
            response = new jTableResponse<Request>("Form invalid while create: " + getBindingErrorMessages(result));
        } else {
            try {
                requestsService.create(request);
                response = new jTableResponse<Request>(request);
            } catch (Exception e) {
                response = new jTableResponse<Request>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }




    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Request> updateRequest(@ModelAttribute Request  request, BindingResult result) {
        jTableResponse<Request> response;
        if (result.hasErrors()) {
            response = new jTableResponse<Request>("Form invalid while update: " + getBindingErrorMessages(result));
        } else {
            try {
                requestsService.update(request);
                response = new jTableResponse<Request>(request);
            } catch (Exception e) {
                response = new jTableResponse<Request>(e.getMessage());
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
