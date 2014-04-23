package pro.tmedia.controller;

import com.google.gson.Gson;
import pro.tmedia.model.DictionaryItem;
import pro.tmedia.model.Request;

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
    final Request Record;
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
    public jTableResponse(Request Record) {
        Result = OK;
        this.Record = Record;

        Message = null;
        Records = null;
        Options = null;
    }

    public String getJSON() {
        return gson.toJson(this);
    }
}
