package pro.tmedia.dao;

import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Request;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Transactional
public interface RequestDAO {
    List<Request> list();
    public void create(Request request);
    public void delete(int id) throws Exception;
    public void update(Request request);
}
