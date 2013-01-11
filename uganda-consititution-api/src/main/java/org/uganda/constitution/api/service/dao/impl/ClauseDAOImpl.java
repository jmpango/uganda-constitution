package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ClauseDAO;
import org.uganda.constitution.api.model.Clause;

/**
 * @author Jonathan
 */
@Repository("clauseDAO")
public class ClauseDAOImpl extends BaseDAOImpl<Clause> implements  ClauseDAO{

}
