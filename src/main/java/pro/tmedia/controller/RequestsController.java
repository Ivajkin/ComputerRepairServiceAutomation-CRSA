package pro.tmedia.controller;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

    final static List<String> requests = new ArrayList<>();
    static {
        Gson gson = new Gson();
        Request request = new Request();
        Hardware hardware = new Hardware();

        hardware.setName("NVIDIA GTX 520");
        request.setHardware(hardware);
        requests.add(gson.toJson(request));

        hardware.setName("NVIDIA GTX 550");
        request.setHardware(hardware);
        requests.add(gson.toJson(request));
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public void searchRequests(HttpServletRequest httpRequest,  HttpServletResponse response) throws IOException {

        String keyword = StringUtils.stripToEmpty(httpRequest.getParameter("q"));
        String text = String.format("Нет возможности найти запрос, содержащий '%s'", keyword);
        logger.info("searchRequests called with '" + keyword + "'");

        response.setContentType("application/json;charset=UTF-8");

        /* ищем запросы */
        List<String> matchedRequests = new ArrayList<>();
        for(String request: requests) {
            if(request.contains(keyword)) {
                matchedRequests.add(request);
            }
        }

        /* генерируем ответ */
        StringBuffer jsonBuffer = new StringBuffer();
        if(!matchedRequests.isEmpty()) {
            jsonBuffer.append("{\"status\":\"found\", \"requests\": [");
            for(String product: matchedRequests) {
                jsonBuffer.append(product + ",");
            }
            jsonBuffer.append("]}");
        } else {
            jsonBuffer.append("{\"status\":\"not found\", \"text\":\"" + text + "\"}");
        }

        response.getWriter().write(jsonBuffer.toString());

    }
}
