package pro.tmedia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.tmedia.model.Fault;
import pro.tmedia.service.DictionaryItemService;

/**
 * User: Ivaykin Timofey
 * Date: 2/16/14
 */
@Controller
public class DictionaryItemController {

    @Autowired
    DictionaryItemService dictionaryItemService;

    final Logger logger = LoggerFactory.getLogger(DictionaryItemController.class);

    @RequestMapping(value = "/fault/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<Fault> listFaults() {
        jTableResponse<Fault> fault;
        try
        {
            fault = new jTableResponse<Fault>(dictionaryItemService.listFaults());
        }
        catch (Exception ex)
        {
            fault = new jTableResponse<Fault>(ex.getMessage());
        }

        logger.info(fault.getJSON());
        return fault;
    }
}
