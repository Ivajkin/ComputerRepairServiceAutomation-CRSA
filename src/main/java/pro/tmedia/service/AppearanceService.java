package pro.tmedia.service;

import pro.tmedia.model.Appearance;
import pro.tmedia.model.Source;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 28.07.2014
 */
public interface AppearanceService {
    public List<Appearance> list();
    public void create(Appearance appearance) throws Exception;
    public void update(Appearance appearance);
    public void delete(int id) throws Exception;
}
