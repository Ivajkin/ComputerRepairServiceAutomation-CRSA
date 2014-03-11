package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.FlowDAO;
import pro.tmedia.model.Fault;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */
@Controller
@RequestMapping("/flow")
public class FlowController extends DictionaryItemController<Fault> {
    @Autowired
    private FlowDAO flowService;

    @Override
    protected Fault getNewDictionaryItem() {
        return new Fault();
    }

    @Override
    protected String createDictionaryItemDB(Fault fault) {
        flowService.create(fault);
        String message = "Поток успешно добавлен: " + fault.getName() + ".";
        return message;
    }

    @Override
    protected List<Fault> getItems() {
        return flowService.findItems();
    }

    @Override
    protected String deleteItem(Integer id) {
        flowService.delete(id);
        String message = "Поток успешно удален.";
        return message;
    }

    @Override
    protected String getDictionaryTypePath() {
        return "/flow";
    }

    @Override
    protected String getPanelHeaderInfo() {
        return "Список потоков";
    }

    @Override
    protected String getPanelDescriptionInfo() {
        return "Здесь вы можете видеть список потоков, удалять и добавлять.";
    }

    @Override
    protected Fault find(Integer id) {
        return flowService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Fault dictionaryItem) {
        flowService.update(dictionaryItem);
    }
}