package pro.tmedia.controller;

import com.google.gson.Gson;

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

    public jTableResponse(String ErrorMessage) {
        Records = null;
        Result = ERROR;
        Message = ErrorMessage;
    }
    public jTableResponse(List<T> Records) {
        Message = null;
        Result = OK;
        this.Records = Records;
    }
    public String getJSON() {
        return gson.toJson(this);
    }
}
