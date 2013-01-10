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
 * Represents the Constitution ObjectiveGroup
 * @author Jonathan
 */
@Entity
@Table(name = "objective_group")
public class ObjectiveGroup extends BaseData {
    private String name;
    private Constitution constitution;
    private int objGroupNumber;
    private String textContent;
    private List<Objective> objectives;

    public ObjectiveGroup(){}

    @ManyToOne()
    @JoinColumn(name = "constitution_id", nullable = false)
    public Constitution getConstitution() {
        return constitution;
    }

    public void setConstitution(Constitution constitution) {
        this.constitution = constitution;
    }

    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "obj_group_number", nullable = true)
    public int getObjGroupNumber() {
        return objGroupNumber;
    }

    public void setObjGroupNumber(int objGroupNumber) {
        this.objGroupNumber = objGroupNumber;
    }

    @Column(name = "text_content", nullable = true)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @OneToMany(mappedBy = "objectiveGroup", cascade = { CascadeType.ALL })
    public List<Objective> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

}
