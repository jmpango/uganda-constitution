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
 * Represents a section under a part in a constitution.
 *
 * @author Jonathan
 */

@Entity
@Table(name = "part_section")
public class Section extends BaseData{
    private String textContent;
    private String sectionNumber;
    private Part part;
    private List<Section> subSections;

    public Section(){}

    @ManyToOne()
    @JoinColumn(name = "part_id", nullable = false)
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Column(name = "section_number", nullable = true)
    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_section", joinColumns = @JoinColumn(name = "part_section_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_section_id"))
    public List<Section> getSubSections() {
        return subSections;
    }

    public void setSubSections(List<Section> subSections) {
        this.subSections = subSections;
    }

    @Column(name = "text_content", nullable = true)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
