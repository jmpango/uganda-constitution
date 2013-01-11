package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ArticleDAO;
import org.uganda.constitution.api.model.Article;

/**
 * @author Jonathan
 */
@Repository("articleDAO")
public class ArticleDAOImpl extends BaseDAOImpl<Article> implements ArticleDAO{

}
