package pro.tmedia.controller;

import com.google.gson.Gson;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pro.tmedia.model.DictionaryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
public class jTableResponse<T> {
    public static class OptionsBean {
        private String DisplayText;
        private Integer Value;

        public OptionsBean(String DisplayText, Integer Value) {
            this.DisplayText = DisplayText;
            this.Value = Value;
        }

        public String getDisplayText() {
            return DisplayText;
        }
        public void setDisplayText(String DisplayText) {
            this.DisplayText = DisplayText;
        }
        public Integer getValue() {
            return Value;
        }
        public void setValue(Integer Value) {
            this.Value = Value;
        }
    }

    static Gson gson = new Gson();

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";

    private final String Result;
    private final String Message;
    final List<T> Records;
    final T Record;
    final List<OptionsBean> Options;

    public jTableResponse(String ErrorMessage) {
        Result = ERROR;
        Message = ErrorMessage;

        Records = null;
        Record = null;
        Options = null;
    }
    public jTableResponse(List<T> Records, boolean convertToOptions) {
        Result = OK;
        if(convertToOptions) {
            List<OptionsBean> Options = new ArrayList<>();
            for(T item : Records) {
                DictionaryItem dictionaryItem = (DictionaryItem)item;
                Options.add(new OptionsBean(dictionaryItem.getName(), dictionaryItem.getId()));
            }
            this.Options = Options;

            this.Records = null;
        } else {
            this.Records = Records;

            this.Options = null;
        }
        this.Record = null;
        this.Message = null;
    }
    public jTableResponse(T Record) {
        Result = OK;
        this.Record = Record;

        Message = null;
        Records = null;
        Options = null;
    }
    public jTableResponse() {
        Result = OK;

        Record = null;
        Message = null;
        Records = null;
        Options = null;
    }

    public String getJSON() {
        return gson.toJson(this);
    }

    public static String getBindingErrorMessages(BindingResult bindingResult) {
        String errorMessage = "";
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            errorMessage += error.getObjectName() + " - " + error.getDefaultMessage() + "\n<br/>";
        }
        return errorMessage;
    }
}
