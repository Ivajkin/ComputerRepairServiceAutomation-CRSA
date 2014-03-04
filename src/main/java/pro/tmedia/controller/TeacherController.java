package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.TeacherDAO;
import pro.tmedia.model.Teacher;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends DictionaryItemController<Teacher>  {
    @Autowired
    private TeacherDAO teacherService;

    @Override
    protected Teacher getNewDictionaryItem() {
        return new Teacher();
    }

    @Override
    protected String createDictionaryItemDB(Teacher teacher) {
        teacherService.create(teacher);
        String message = "Запись о преподавателе успешно добавлена: " + teacher.getName() + ".";
        return message;
    }

    @Override
    protected List<Teacher> getItems() {
        return teacherService.findItems();
    }

    @Override
    protected String deleteItem(Integer id) {
        teacherService.delete(id);
        String message = "Запись о преподавателе успешно удалена.";
        return message;
    }

    @Override
    protected String getDictionaryTypePath() {
        return "/teacher";
    }

    @Override
    protected String getPanelHeaderInfo() {
        return "Список записей о преподавателях";
    }

    @Override
    protected String getPanelDescriptionInfo() {
        return "Здесь вы можете видеть список записей о преподавателях, удалять и добавлять.";
    }

    @Override
    protected Teacher find(Integer id) {
        return teacherService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Teacher dictionaryItem) {
        teacherService.update(dictionaryItem);
    }
}
