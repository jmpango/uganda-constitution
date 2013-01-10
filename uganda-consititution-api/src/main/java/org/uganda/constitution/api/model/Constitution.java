package org.uganda.constitution.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents the constitution hierarchy.
 * @author Jonathan
 */
@Entity
@Table(name = "constitution")
public class Constitution extends BaseData{
    private String name;
    private List<ObjectiveGroup> objectiveGroups;
    private List<Chapter>   chapters;
    private List<Schedule>  schedules;
    private String language;
    private int year;

    public Constitution(){}

    @OneToMany(mappedBy = "constitution", cascade = { CascadeType.ALL })
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
    
    @OneToMany(mappedBy = "constitution", cascade = { CascadeType.ALL })
    public List<ObjectiveGroup> getObjectiveGroups() {
        return objectiveGroups;
    }

    public void setObjectiveGroups(List<ObjectiveGroup> objectiveGroups) {
        this.objectiveGroups = objectiveGroups;
    }

    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "constitution", cascade = { CascadeType.ALL })
    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Column(name = "c_language", nullable = true)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "c_year", nullable = true)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
