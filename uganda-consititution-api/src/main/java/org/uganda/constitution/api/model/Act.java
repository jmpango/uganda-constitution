package org.uganda.constitution.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents an act in a constitution.
 *
 * @author Jonathan
 */

@Entity
@Table(name = "act")
public class Act extends BaseData{
    private String title;
    private String actNumber;
    private List<Part> parts;
    private Article article;
    private List<RuleOfProcedure> ruleOfProcedure;

    public Act(){}

    @Column(name = "act_number", nullable = true)
    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }

    @ManyToOne()
    @JoinColumn(name = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @OneToMany(mappedBy = "act", cascade = { CascadeType.ALL })
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @OneToMany(mappedBy = "act", cascade = { CascadeType.ALL })
    public List<RuleOfProcedure> getRuleOfProcedure() {
        return ruleOfProcedure;
    }

    public void setRuleOfProcedure(List<RuleOfProcedure> ruleOfProcedure) {
        this.ruleOfProcedure = ruleOfProcedure;
    }

    @Column(name = "title", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
