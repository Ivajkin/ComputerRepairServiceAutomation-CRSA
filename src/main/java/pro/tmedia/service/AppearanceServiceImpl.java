package pro.tmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.dao.AppearanceDAO;
import pro.tmedia.model.Appearance;

import java.util.List;

/**
 * User: Ivaykin Timofey
 * Date: 09.04.14
 */
@Service
@Transactional
public class AppearanceServiceImpl implements AppearanceService {
    @Autowired
    AppearanceDAO appearanceDAO;

    public List<Appearance> list() {
        return appearanceDAO.list();
    }

    @Override
    public void create(Appearance appearance) throws Exception {
        appearanceDAO.create(appearance);
    }

    @Override
    public void delete(int id) throws Exception {
        appearanceDAO.delete(id);
    }

    @Override
    public void update(Appearance appearance) {
        appearanceDAO.update(appearance);
    }
}
