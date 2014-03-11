package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.DisciplineDAO;
import pro.tmedia.model.Appearance;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Controller
@RequestMapping("/discipline")
public class DisciplineController extends DictionaryItemController<Appearance> {
    @Autowired
    private DisciplineDAO disciplineService;

    @Override
    protected Appearance getNewDictionaryItem() {
        return new Appearance();
    }

    @Override
    protected String createDictionaryItemDB(Appearance appearance) {
        disciplineService.create(appearance);
        String message = "Дисциплина успешно добавлена: " + appearance.getName() + ".";
        return message;
    }

    @Override
    protected List<Appearance> getItems() {
        return disciplineService.findItems();
    }

    @Override
    protected String deleteItem(Integer id) {
        disciplineService.delete(id);
        String message = "Дисциплина успешно удалена.";
        return message;
    }

    @Override
    protected String getDictionaryTypePath() {
        return "/discipline";
    }

    @Override
    protected String getPanelHeaderInfo() {
        return "Список дисциплин";
    }

    @Override
    protected String getPanelDescriptionInfo() {
        return "Здесь вы можете видеть список дисциплин, удалять и добавлять.";
    }

    @Override
    protected Appearance find(Integer id) {
        return disciplineService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Appearance dictionaryItem) {
        disciplineService.update(dictionaryItem);
    }
}
