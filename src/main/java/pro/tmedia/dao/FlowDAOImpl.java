package pro.tmedia.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.tmedia.model.Flow;

/**
 * User: Ivaykin Timofey
 * Date: 2/12/14
 */
@Repository
@Transactional
public final class FlowDAOImpl extends DictionaryItemDAOImpl<Flow> implements FlowDAO {
}
