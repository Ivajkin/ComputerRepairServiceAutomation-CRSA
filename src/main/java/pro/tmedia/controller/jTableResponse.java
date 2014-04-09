package pro.tmedia.controller;

import com.google.gson.Gson;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
public class jTableResponse<T> {
    static Gson gson = new Gson();

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";

    private final String Result;
    private final String Message;
    final List<T> Records;
    final Request Record;

    public jTableResponse(String ErrorMessage) {
        Result = ERROR;
        Message = ErrorMessage;

        Records = null;
        Record = null;
    }
    public jTableResponse(List<T> Records) {
        Result = OK;
        this.Records = Records;

        Message = null;
        Record = null;
    }
    public jTableResponse(Request Record) {
        Result = OK;
        this.Record = Record;

        Message = null;
        Records = null;
    }

    public String getJSON() {
        return gson.toJson(this);
    }
}
