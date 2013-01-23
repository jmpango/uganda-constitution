package org.uganda.constitution.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a clause in a constitution.
 *
 * @author Jonathan
 */

@Entity
@Table(name = "clause")
public class Clause extends BaseData {
    private int clauseNumber;
    private String textContent;
    private List<Clause> clauses;
    private Article article;
    private Objective objective;
    private Schedule schedule;

    public Clause(){}

    @Column(name = "clause_number", nullable = true)
    public int getClauseNumber() {
        return clauseNumber;
    }

    public void setClauseNumber(int clauseNumber) {
        this.clauseNumber = clauseNumber;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_clause", joinColumns = @JoinColumn(name = "clause_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_clause_id"))
    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    @Column(name = "text_content", nullable = true,length=10000)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @ManyToOne()
    @JoinColumn(name = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne()
    @JoinColumn(name = "objective_id", nullable = false)
    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
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
