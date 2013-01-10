package org.uganda.constitution.api.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.dao.ActDAO;
import org.uganda.constitution.api.model.Act;

/**
 * @author Jonathan
 */
@Repository("actDAO")
public class ActDAOImpl extends BaseDAOImpl<Act> implements ActDAO {

}
