package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.RuleDAO;
import org.uganda.constitution.api.model.Rule;

/**
 * @author Jonathan
 */
@Repository("ruleDAO")
public class RuleDAOImpl extends BaseDAOImpl<Rule> implements RuleDAO{

}
