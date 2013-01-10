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
 * Represents an article in a constitution.
 *
 * @author Jonathan
 */

@Entity
@Table(name = "article")
public class Article extends BaseData {
    private String textContent;
    private int articleNumber;
    private List<Clause> clauses;
    private Chapter chapter;
    private List<Act> acts;
    private Schedule  schedule;

    public Article(){}

    @OneToMany(mappedBy = "article", cascade = { CascadeType.ALL })
    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    @Column(name = "article_number", nullable = true)
    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    @ManyToOne()
    @JoinColumn(name = "chapter_id", nullable = false)
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @OneToMany(mappedBy = "article", cascade = { CascadeType.ALL })
    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    @Column(name = "text_content", nullable = true)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

     @ManyToOne()
    @JoinColumn(name = "schedule_id", nullable = false)
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


}
