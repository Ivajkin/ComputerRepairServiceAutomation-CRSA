package pro.tmedia.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
public abstract class DictionaryItemController<T> {

    @RequestMapping(value = "/create")
    public final ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("create-dictionary-item-form");
        modelAndView.addObject("dictionaryItem", getNewDictionaryItem());
        modelAndView.addObject("dictionaryTypePath", getDictionaryTypePath());
        modelAndView.addObject("processPath", "/create/process");

        return modelAndView;
    }

    @RequestMapping(value = "/create/process")
    public final ModelAndView creating(@ModelAttribute T dictionaryItem) {
        ModelAndView modelAndView = new ModelAndView("home");

        String message = createDictionaryItemDB(dictionaryItem);
        modelAndView.addObject("message", message );
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    public final ModelAndView listOfItems() {
        ModelAndView modelAndView = new ModelAndView("list-of-dictionary-items");

        List<T> dictionaryItems = getItems();

        modelAndView.addObject("dictionaryItems", dictionaryItems);
        modelAndView.addObject("dictionaryTypePath", getDictionaryTypePath());
        modelAndView.addObject("panelHeaderInfo", getPanelHeaderInfo());
        modelAndView.addObject("panelDescriptionInfo", getPanelDescriptionInfo());

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public final ModelAndView delete(@PathVariable Integer id) {
        String message = deleteItem(id);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editDictionaryItem(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("create-dictionary-item-form");

        T dictionaryItem = find(id);
        modelAndView.addObject("dictionaryItem",dictionaryItem);
        modelAndView.addObject("dictionaryTypePath", getDictionaryTypePath());
        modelAndView.addObject("processPath", "/edit/"+id);

        return modelAndView;
    }
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingDictionaryItem(@ModelAttribute T dictionaryItem, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("home");
        updateDictionaryItem(dictionaryItem);

        String message = "Объект словаря был успешно изменен.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }


    protected abstract T getNewDictionaryItem();
    protected abstract String createDictionaryItemDB(T item);
    protected abstract List<T> getItems();
    protected abstract String deleteItem(Integer id);
    protected abstract String getDictionaryTypePath();
    protected abstract String getPanelHeaderInfo();
    protected abstract String getPanelDescriptionInfo();
    protected abstract T find(final Integer id);
    protected abstract void updateDictionaryItem(T dictionaryItem);
}
