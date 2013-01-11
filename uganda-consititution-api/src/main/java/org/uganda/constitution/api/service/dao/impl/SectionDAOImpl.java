package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.SectionDAO;
import org.uganda.constitution.api.model.Section;

/**
 * @author Jonathan
 */
@Repository("sectionDAO")
public class SectionDAOImpl extends BaseDAOImpl<Section> implements SectionDAO {

}
