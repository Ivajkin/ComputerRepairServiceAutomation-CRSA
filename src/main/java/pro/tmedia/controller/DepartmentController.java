package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pro.tmedia.dao.DepartmentDAO;
import pro.tmedia.dao.DisciplineDAO;
import pro.tmedia.model.Department;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentDAO departmentService;

    @RequestMapping(value = "/create")
    public final ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("/department/create");

        modelAndView.addObject("department", new Department());

        return modelAndView;
    }

    @RequestMapping(value = "/create/process")
    public final ModelAndView creating(@ModelAttribute Department department) {
        ModelAndView modelAndView = new ModelAndView("home");

        departmentService.create(department);
        String message = "Кафедра успешно добавлена: " + department.getName() + ".";

        modelAndView.addObject("message", message );
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public final ModelAndView listOfItems() {
        ModelAndView modelAndView = new ModelAndView("/department/list");

        List<Department> departments = departmentService.findItems();

        modelAndView.addObject("departments", departments);

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public final ModelAndView delete(@PathVariable Integer id) {

        departmentService.delete(id);
        String message = "Кафедра успешно удалена.";

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
    /*
    FOR UPDATE:
    @RequestMapping(value="/team/edit/{id}", method=RequestMethod.GET)
    39
    public ModelAndView editTeamPage(@PathVariable Integer id) {
        40
        ModelAndView modelAndView = new ModelAndView("edit-team-form");
        41
        Team team = teamService.getTeam(id);
        42
        modelAndView.addObject("team",team);
        43
        return modelAndView;
        44
    }
    @RequestMapping(value="/team/edit/{id}", method=RequestMethod.POST)
47
    public ModelAndView edditingTeam(@ModelAttribute Team team, @PathVariable Integer id) {
48

49
        ModelAndView modelAndView = new ModelAndView("home");
50

51
        teamService.updateTeam(team);
52

53
        String message = "Team was successfully edited.";
54
        modelAndView.addObject("message", message);
55

56
        return modelAndView;
57
    }

           */
}
