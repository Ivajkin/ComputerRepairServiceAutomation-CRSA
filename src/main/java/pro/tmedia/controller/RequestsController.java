package pro.tmedia.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping("/requests")
public class RequestsController {

    final Logger logger = LoggerFactory.getLogger(RequestsController.class);

    static Gson gson = new Gson();

    final static List<Request> requests = new ArrayList<>();
    static {
        Request request;
        Hardware hardware;

        request = new Request();
        hardware = new Hardware();
        hardware.setName("NVIDIA GTX 520");
        request.setHardware(hardware);
        request.setAmount(12);
        requests.add(request);

        request = new Request();
        hardware = new Hardware();
        hardware.setName("NVIDIA GTX 550");
        request.setHardware(hardware);
        request.setAmount(5);
        requests.add(request);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void listRequests(HttpServletRequest httpRequest,  HttpServletResponse response) throws IOException {

        logger.info("Requests list requested");

        response.setContentType("application/json;charset=UTF-8");



        String json;
        try
        {
            json = new jTableResponse<Request>(requests).getJSON();
        }
        catch (Exception ex)
        {
            json = new jTableResponse<Request>(ex.getMessage()).getJSON();
        }

        response.getWriter().write(json);


    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createRequest(HttpServletRequest httpRequest,  HttpServletResponse response, @RequestBody Request request) throws IOException {


        requests.add(request);

        String message = "{\"status\": \"Request created\", \"object\":  " + gson.toJson(request) + "}";
        logger.info(message);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(message);
    }


}
