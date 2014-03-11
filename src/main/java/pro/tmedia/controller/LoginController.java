package pro.tmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.tmedia.model.Employee;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public final ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("login_page");
        modelAndView.addObject("user", new Employee());
        return modelAndView;
    }
}
