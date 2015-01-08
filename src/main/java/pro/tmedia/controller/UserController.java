package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Employee;
import pro.tmedia.model.Hardware;
import pro.tmedia.model.Role;
import pro.tmedia.service.HardwareService;
import pro.tmedia.service.UserService;

/**
 * User: Ivaykina Galina
 * Date: 8/06/14
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    final Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Employee> listEmployee(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Employee> response;
        try
        {
            response = new jTableResponse<>(userService.list(), false);
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
    public jTableResponse<Employee> createUser(@ModelAttribute Employee  employee, BindingResult result) {
        jTableResponse<Employee> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(employee)));
                userService.create(employee);
                response = new jTableResponse<>(employee);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Employee> updateEmployee(@ModelAttribute Employee  employee, BindingResult result) {
        jTableResponse<Employee> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                userService.update(employee);
                response = new jTableResponse<>(employee);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }



    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee currentUser() throws Exception {
        return userService.getCurrentSessionUser();
    }
}
