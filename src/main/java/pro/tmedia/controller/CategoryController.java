package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Appearance;
import pro.tmedia.model.Category;
import pro.tmedia.service.AppearanceService;
import pro.tmedia.service.CategoryService;
import pro.tmedia.service.DictionaryItemService;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    DictionaryItemService dictionaryItemService;
    @Autowired
    CategoryService categoryService;

    final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Category> list(@RequestParam("jtStartIndex") int startIndex,
                                                          @RequestParam("jtPageSize") int pageSize,
                                                          @RequestParam("jtSorting") String sorting) {
        jTableResponse<Category> response;
        try
        {
            response = new jTableResponse<>(dictionaryItemService.listCategories(), false);
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
    public jTableResponse<Category> create(@ModelAttribute Category category, BindingResult result) {
        jTableResponse<Category> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while create: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {

                logger.info("Creating: ".concat(RequestsController.gson.toJson(category)));
                categoryService.create(category);
                response = new jTableResponse<>(category);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Category> delete(@RequestParam Integer id) {
        jTableResponse<Category> response;
        try {
            categoryService.delete(id);
            response = new jTableResponse<>();
        } catch (Exception e) {
            response = new jTableResponse<>(e.getMessage());
            logger.error(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Category> update(@ModelAttribute Category category, BindingResult result) {
        jTableResponse<Category> response;
        if (result.hasErrors()) {
            response = new jTableResponse<>("Form invalid while update: " + jTableResponse.getBindingErrorMessages(result));
        } else {
            try {
                categoryService.update(category);
                response = new jTableResponse<>(category);
            } catch (Exception e) {
                response = new jTableResponse<>(e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

}
