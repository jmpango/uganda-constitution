package org.uganda.constitution.api.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.dao.ChapterDAO;
import org.uganda.constitution.api.model.Chapter;

/**
 * @author Jonathan
 */
@Repository("chapterDAO")
public class ChapterDAOImpl extends BaseDAOImpl<Chapter> implements ChapterDAO{

}
