package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.PartDAO;
import org.uganda.constitution.api.model.Part;

/**
 * @author Jonathan
 */
@Repository("partDAO")
public class PartDAOImpl extends BaseDAOImpl<Part> implements PartDAO{

}
