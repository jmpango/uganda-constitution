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
 * Represents a part under an Act in a constitution
 *
 * @author Jonathan
 */

@Entity
@Table(name = "part")
public class Part extends BaseData{
    private String title;
    private int partNumber;
    private List<Section> sections;
    private List<Part> subParts;
    private Act act;

    public Part(){}

    @ManyToOne()
    @JoinColumn(name = "act_id", nullable = true)
    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

    @Column(name = "part_number", nullable = true)
    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    @OneToMany(mappedBy = "part", cascade = { CascadeType.ALL })
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_part", joinColumns = @JoinColumn(name = "part_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_part_id"))
    public List<Part> getSubParts() {
        return subParts;
    }

    public void setSubParts(List<Part> subParts) {
        this.subParts = subParts;
    }

    @Column(name = "title", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
