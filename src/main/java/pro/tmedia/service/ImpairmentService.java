package pro.tmedia.service;

import pro.tmedia.model.Impairment;

import java.util.List;

/**
 * User: Ivaykina Galina
 * Date: 04.03.15
 */
public interface ImpairmentService {
    public List<Impairment> list(Integer id);
    public void create(Impairment impairment) throws Exception;
    public void delete(int id) throws Exception;
    public void update(Impairment impairment);
}
