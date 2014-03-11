package pro.tmedia.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping("/requests")
public class RequestsController {
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public void searchRequests(Locale locale, Model model, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{hardware: {name: \"NVIDIA GTX 520\"} }");
    }
}
