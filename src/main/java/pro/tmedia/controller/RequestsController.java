package pro.tmedia.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Request;
import pro.tmedia.service.RequestsService;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */

/* TODO: прописать комментарии на API и сделать по нему автоматически генерируемую документацию */
@Controller
@RequestMapping("/requests")
public class RequestsController {

    static Gson gson = new Gson();

    @Autowired
    RequestsService requestsService;

    final Logger logger = LoggerFactory.getLogger(RequestsController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Request> listRequests(/*@RequestParam("jtStartIndex") int startIndex,
                                                @RequestParam("jtPageSize") int pageSize,
                                                @RequestParam("jtSorting") String sorting*/) {
        jTableResponse<Request> response;
        try
        {
            response = new jTableResponse<>(requestsService.list(), false);
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
    public jTableResponse<Request> createRequest(@ModelAttribute Request  request, BindingResult result) {
        jTableResponse<Request> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(gson.toJson(request)));
                requestsService.create(request);
                response = new jTableResponse<>(request);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
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
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                requestsService.update(request);
                response = new jTableResponse<>(request);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Request> deleteWarehouseItem(@RequestParam Integer req_num_id) {
        jTableResponse<Request> response;
        try {
            requestsService.delete(req_num_id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }


}
