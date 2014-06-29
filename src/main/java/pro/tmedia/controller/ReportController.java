package pro.tmedia.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pro.tmedia.model.Request;
import pro.tmedia.model.cash.CashOperation;
import pro.tmedia.service.RequestsService;
import pro.tmedia.service.cash.CashTypeService;

/**
 * User: Ivaykina Galina
 * Date: 29/06/14
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    CashTypeService cashTypeService;

    final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/cash-operations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public jTableResponse<CashOperation> showOperationReport() {
        jTableResponse<CashOperation> response;
        try
        {
            response = new jTableResponse<>(cashTypeService.listOperations(), false);
        }
        catch (Exception ex)
        {
            response = new jTableResponse<>(ex.getMessage());
            logger.error(ex.getMessage());
        }

        logger.info(response.getJSON());
        return response;
    }


}
