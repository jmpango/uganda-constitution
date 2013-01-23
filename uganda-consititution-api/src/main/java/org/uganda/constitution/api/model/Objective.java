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
 * Represents the Constitution Objective.
 * @author Jonathan
 */
@Entity
@Table(name = "objective")
public class Objective extends BaseData {
    private ObjectiveGroup objectiveGroup;
    private String objectiveNumber;
    private String textContent;
    private List<Clause> clauses;

    public Objective(){}

    @OneToMany(mappedBy = "objective", cascade = { CascadeType.ALL })
    public List<Clause> getClauses() {
        return clauses;
    }

    public void setClauses(List<Clause> clauses) {
        this.clauses = clauses;
    }

    @ManyToOne()
    @JoinColumn(name = "objective_group_id", nullable = false)
    public ObjectiveGroup getObjectiveGroup() {
        return objectiveGroup;
    }

    public void setObjectiveGroup(ObjectiveGroup objectiveGroup) {
        this.objectiveGroup = objectiveGroup;
    }

    @Column(name = "objective_number", nullable = true)
    public String getObjectiveNumber() {
        return objectiveNumber;
    }

    public void setObjectiveNumber(String objectiveNumber) {
        this.objectiveNumber = objectiveNumber;
    }

    @Column(name = "text_content", nullable = true,length=10000)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
