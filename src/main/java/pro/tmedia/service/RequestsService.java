package pro.tmedia.service;

import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
public interface RequestsService {
    public List<Request> list();
    public void create(Request request) throws Exception;
    public void delete(int id) throws Exception;
    public void update(Request request);
}
