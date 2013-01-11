package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ObjectiveDAO;
import org.uganda.constitution.api.model.Objective;

/**
 * @author Jonathan
 */
@Repository("objectiveDAO")
public class ObjectiveDAOImpl extends BaseDAOImpl<Objective> implements ObjectiveDAO{

}
