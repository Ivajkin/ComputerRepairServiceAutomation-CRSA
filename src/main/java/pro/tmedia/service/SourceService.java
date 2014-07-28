package pro.tmedia.service;

import pro.tmedia.model.Employee;
import pro.tmedia.model.Role;
import pro.tmedia.model.Source;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
public interface SourceService {
    public List<Source> list();
    public void create(Source source) throws Exception;
    public void update(Source source);
    public void delete(int id) throws Exception;
}
