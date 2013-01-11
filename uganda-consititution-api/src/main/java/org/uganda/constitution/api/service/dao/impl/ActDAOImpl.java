package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ActDAO;
import org.uganda.constitution.api.model.Act;

/**
 * @author Jonathan
 */
@Repository("actDAO")
public class ActDAOImpl extends BaseDAOImpl<Act> implements ActDAO {

}
