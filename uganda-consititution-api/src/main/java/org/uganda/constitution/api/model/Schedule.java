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
 * Represents a schedule in a constitution.
 * 
 * @author Jonathan
 */

@Entity
@Table(name = "schedule")
public class Schedule  extends BaseData{
    private int schedule_number;
    private String schedule_title;
    private String textContent;
    private List<Article> articles;
    private Constitution constitution;
    private List<Clause> clauses;

    public Schedule(){}

    @OneToMany(mappedBy = "schedule", cascade = { CascadeType.ALL })
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @OneToMany(mappedBy = "schedule", cascade = { CascadeType.ALL })
    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    @ManyToOne()
    @JoinColumn(name = "constitution_id", nullable = false)
    public Constitution getConstitution() {
        return constitution;
    }

    public void setConstitution(Constitution constitution) {
        this.constitution = constitution;
    }

    @Column(name = "schedule_number", nullable = true)
    public int getSchedule_number() {
        return schedule_number;
    }

    public void setSchedule_number(int schedule_number) {
        this.schedule_number = schedule_number;
    }

    @Column(name = "schedule_title", nullable = true)
    public String getSchedule_title() {
        return schedule_title;
    }

    public void setSchedule_title(String schedule_title) {
        this.schedule_title = schedule_title;
    }

    @Column(name = "text_content", nullable = true,length=10000)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
