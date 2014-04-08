package pro.tmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/process", method = RequestMethod.POST)
    public ModelAndView setupForm(@RequestParam("login") String login, @RequestParam("password") String password) {
        if(login.equals("Vasya") && password.equals("12345678")) {
            ModelAndView page = new ModelAndView("redirect:/");
            page.addObject("login", login);
            return page;
        }   else {
            ModelAndView page = new ModelAndView("login");
            page.addObject("message", "Неверный логин или пароль!");
            return page;
        }
    }
/*    @RequestMapping(value = "/login")
    public final ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("login_page");
        modelAndView.addObject("user", new Employee());
        return modelAndView;
    }
*/
}

