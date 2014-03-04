package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pro.tmedia.model.*;
import pro.tmedia.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Ivaykin Timofey
 */
@Controller
@RequestMapping("/report")
public class ReportEntityController {
    @Autowired
    private ReportEntryService reportEntryService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private FlowService flowService;

    // TODO: доделать поля выбора отчета
    @RequestMapping(value = "/select")
    public final ModelAndView selectReport() {
        ModelAndView modelAndView = new ModelAndView("/report/select");

        modelAndView.addObject("reportSelectForm", new Report());
        //List<ReportEntry> reportEntries = reportEntryService.findItems();
        //modelAndView.addObject("reportEntries", reportEntries);


        List<Semester> semesterList = semesterService.findItems();
        modelAndView.addObject("semesterList", semesterList);
        List<Department> departmentList = departmentService.findItems();
        modelAndView.addObject("departmentList", departmentList);
        List<Teacher> teacherList = teacherService.findItems();
        modelAndView.addObject("teacherList", teacherList);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editReportEntryPage(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("/report/create");
        ReportEntry reportEntry = reportEntryService.find(id);
        modelAndView.addObject("reportEntryForm", reportEntry);


        List<Semester> semesterList = semesterService.findItems();
        modelAndView.addObject("semesterList", semesterList);

        List<Department> departmentList = departmentService.findItems();
        modelAndView.addObject("departmentList", departmentList);

        List<Teacher> teacherList = teacherService.findItems();
        modelAndView.addObject("teacherList", teacherList);

        List<Discipline> disciplineList = disciplineService.findItems();
        modelAndView.addObject("disciplineList", disciplineList);

        modelAndView.addObject("processPath", "/report/edit/"+id);

        List<Flow> flowList = flowService.findItems();
        modelAndView.addObject("flowList", flowList);


        return modelAndView;
    }
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingReportEntry(@ModelAttribute ReportEntry reportEntryForm, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        reportEntryService.update(reportEntryForm);

        String message = "Запись отчета (нагрузка) успешно изменена.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteReportEntry(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        reportEntryService.delete(id);

        String message = "Запись отчета (нагрузка) успешно удалена.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/list"/*/{sem_id}/{dept_id}/{teacher_id}*/, method=RequestMethod.GET)
    public final ModelAndView listOfItems
                        (@ModelAttribute Report reportSelectForm
                        /*@PathVariable Integer sem_id,
                         @PathVariable Integer dept_id,
                         @PathVariable Integer teacher_id*/) {
        ModelAndView modelAndView = new ModelAndView("/report/list");

        modelAndView.addObject("reportSelectForm", new ReportEntry());

        /*List<ReportEntry> reportEntries = reportEntryService.findItems(reportSelectForm.getSem().getId(),
                reportSelectForm.getDept().getId(), reportSelectForm.getTeacher().getId()); */
        List<ReportEntry> reportEntries = reportEntryService.findItems(reportSelectForm.getSem().getId(),
                reportSelectForm.getDept().getId(),
                reportSelectForm.getTeacher().getId());
        modelAndView.addObject("reportEntries", reportEntries/*reportSelectForm.getReportEntries()*/);

        if(reportEntries.size() > 0)
            modelAndView.addObject("report", reportEntries.get(0).getReport());
        else
            modelAndView.addObject("report", reportSelectForm);

        return modelAndView;
    }

    /*@RequestMapping(value = "/actions")
     public final ModelAndView actionPage(@ModelAttribute ReportEntry reportEntry) {
        ModelAndView modelAndView = new ModelAndView("create-report-entry");

        modelAndView.addObject("newReportEntry", new ReportEntry());

        return modelAndView;
    }   */

    @RequestMapping(value = "/create")
    public final ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/report/create");
        modelAndView.addObject("reportEntryFullForm", new ReportWithReportEntry());


        List<Semester> semesterList = semesterService.findItems();
        modelAndView.addObject("semesterList", semesterList);

        List<Department> departmentList = departmentService.findItems();
        modelAndView.addObject("departmentList", departmentList);

        List<Teacher> teacherList = teacherService.findItems();
        modelAndView.addObject("teacherList", teacherList);

        List<Discipline> disciplineList = disciplineService.findItems();
        modelAndView.addObject("disciplineList", disciplineList);

        List<Flow> flowList = flowService.findItems();
        modelAndView.addObject("flowList", flowList);

        modelAndView.addObject("processPath", "/report/create/process");


        return modelAndView;
    }

    @RequestMapping(value = "/create/process", method = RequestMethod.POST)
    public final ModelAndView creating(@ModelAttribute ReportWithReportEntry reportEntryFullForm, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("home");

        if(/*reportService.find(reportEntryFullForm.report.getId())*/reportEntryFullForm.report.getId() == null) {
            Report rep = new Report();
            rep.setSem(reportEntryFullForm.report.getSem());
            rep.setDept(reportEntryFullForm.report.getDept());
            rep.setTeacher(reportEntryFullForm.report.getTeacher());
            reportService.create(rep);
            reportEntryFullForm.report = rep;
        }
        reportEntryFullForm.reportEntry.setReport(reportEntryFullForm.report);
        reportEntryService.create(reportEntryFullForm.reportEntry);

        if (bindingResult.hasErrors()) {
            String message = "Ошибка bindingResult.hasErrors.";
            modelAndView.addObject("message", message );
        } else {
            String message = "Запись отчета успешно добавлена.";
            modelAndView.addObject("message", message );
        }

        return modelAndView;
    }

    /*protected Map referenceData(HttpServletRequest request) throws Exception {

        Map referenceData = new HashMap();

        List<Semester> semesterList = semesterService.findItems();
        Map<String,String> semesterMap = new LinkedHashMap<String,String>();
        for(int i = 0; i < semesterList.size(); ++i) {
            semesterMap.put(semesterList.get(i).getName(), semesterList.get(i).getName());
        }
        referenceData.put("semesterList", semesterMap);

        return referenceData;
    }  */
}
