package org.uganda.constitution.api.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.dao.ConstitutionDAO;
import org.uganda.constitution.api.model.Constitution;

/**
 * @author Jonathan
 */
@Repository("constitutionDAO")
public class ConstitutionDAOImpl extends BaseDAOImpl<Constitution> implements ConstitutionDAO{

}
