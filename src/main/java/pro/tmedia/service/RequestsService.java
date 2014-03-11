package pro.tmedia.service;

import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 3/11/14
 */
public interface RequestsService {
    public List<Request> findByNameContains(String name);
}
