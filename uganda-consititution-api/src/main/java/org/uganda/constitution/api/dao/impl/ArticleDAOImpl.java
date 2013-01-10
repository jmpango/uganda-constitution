package org.uganda.constitution.api.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.dao.ArticleDAO;
import org.uganda.constitution.api.model.Article;

/**
 * @author Jonathan
 */
@Repository("articleDAO")
public class ArticleDAOImpl extends BaseDAOImpl<Article> implements ArticleDAO{

}
