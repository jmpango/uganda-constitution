package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ConstitutionDAO;
import org.uganda.constitution.api.model.Constitution;

/**
 * @author Jonathan
 */
@Repository("constitutionDAO")
public class ConstitutionDAOImpl extends BaseDAOImpl<Constitution> implements ConstitutionDAO{

}
