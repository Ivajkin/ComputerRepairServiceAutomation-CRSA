package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.TeacherDAO;
import pro.tmedia.model.Manufacturer;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends DictionaryItemController<Manufacturer>  {
    @Autowired
    private TeacherDAO teacherService;

    @Override
    protected Manufacturer getNewDictionaryItem() {
        return new Manufacturer();
    }

    @Override
    protected String createDictionaryItemDB(Manufacturer manufacturer) {
        teacherService.create(manufacturer);
        String message = "Запись о преподавателе успешно добавлена: " + manufacturer.getName() + ".";
        return message;
    }

    @Override
    protected List<Manufacturer> getItems() {
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
    protected Manufacturer find(Integer id) {
        return teacherService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Manufacturer dictionaryItem) {
        teacherService.update(dictionaryItem);
    }
}
