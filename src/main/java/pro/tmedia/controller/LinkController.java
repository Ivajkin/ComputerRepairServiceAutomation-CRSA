package pro.tmedia.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pro.tmedia.model.Employee;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Controller
public class LinkController {

    @RequestMapping(value = "/")
    public ModelAndView mainPage() {
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView page = new ModelAndView("login");
        if(error != null) page.addObject("error", "Неверный логин или пароль!");
        if(logout != null) page.addObject("logout", "Вы успешно вышли");
        return page;
    }
}
