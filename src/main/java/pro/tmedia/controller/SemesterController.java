package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.SemesterDAO;
import pro.tmedia.model.Semester;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */
@Controller
@RequestMapping("/semester")
public class SemesterController extends DictionaryItemController<Semester> {
    @Autowired
    private SemesterDAO semesterService;

    @Override
    protected Semester getNewDictionaryItem() {
        return new Semester();
    }

    @Override
    protected String createDictionaryItemDB(Semester semester) {
        semesterService.create(semester);
        String message = "Семестр успешно добавлен: " + semester.getName() + ".";
        return message;
    }

    @Override
    protected List<Semester> getItems() {
        return semesterService.findItems();
    }

    @Override
    protected String deleteItem(Integer id) {
        semesterService.delete(id);
        String message = "Семестр успешно удален.";
        return message;
    }

    @Override
    protected String getDictionaryTypePath() {
        return "/semester";
    }

    @Override
    protected String getPanelHeaderInfo() {
        return "Список семестров";
    }

    @Override
    protected String getPanelDescriptionInfo() {
        return "Здесь вы можете видеть список семестров, удалять и добавлять.";
    }

    @Override
    protected Semester find(Integer id) {
        return semesterService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Semester dictionaryItem) {
        semesterService.update(dictionaryItem);
    }
}