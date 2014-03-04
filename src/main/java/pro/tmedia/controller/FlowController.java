package pro.tmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.tmedia.dao.FlowDAO;
import pro.tmedia.model.Flow;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/18/14
 */
@Controller
@RequestMapping("/flow")
public class FlowController extends DictionaryItemController<Flow> {
    @Autowired
    private FlowDAO flowService;

    @Override
    protected Flow getNewDictionaryItem() {
        return new Flow();
    }

    @Override
    protected String createDictionaryItemDB(Flow flow) {
        flowService.create(flow);
        String message = "Поток успешно добавлен: " + flow.getName() + ".";
        return message;
    }

    @Override
    protected List<Flow> getItems() {
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
    protected Flow find(Integer id) {
        return flowService.find(id);
    }

    @Override
    protected void updateDictionaryItem(Flow dictionaryItem) {
        flowService.update(dictionaryItem);
    }
}