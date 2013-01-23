package org.uganda.constitution.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 * Represents the constitution hierarchy.
 * @author Jonathan
 */
@Entity
@Table(name = "constitution")
public class Constitution extends BaseData {

    private String name;
    private List<ObjectiveGroup> objectiveGroups;
    private List<Chapter> chapters;
    private List<Schedule> schedules;
    private String language;
    private int year;

    public Constitution() {
    }

    @OneToMany(mappedBy = "constitution", cascade = {CascadeType.ALL})
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
        org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.PERSIST,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @OneToMany(mappedBy = "constitution")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
        org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.PERSIST,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
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

    @OneToMany(mappedBy = "constitution", cascade = {CascadeType.ALL})
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
        org.hibernate.annotations.CascadeType.DELETE,
        org.hibernate.annotations.CascadeType.MERGE,
        org.hibernate.annotations.CascadeType.PERSIST,
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
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

    public void addObjectiveGroup(ObjectiveGroup objectiveGroup) {
        if (objectiveGroup == null) {
            return;
        }

        if (getObjectiveGroups() == null) {
            setObjectiveGroups(new ArrayList<ObjectiveGroup>());
        }

        getObjectiveGroups().add(objectiveGroup);
    }

    public void removeObjectiveGroup(ObjectiveGroup objectiveGroup) {
        if (objectiveGroup == null || getObjectiveGroups() == null) {
            return;
        }

        getObjectiveGroups().remove(objectiveGroup);
    }

    public void addSchedules(Schedule schedule) {
        if (schedule == null) {
            return;
        }

        if (getSchedules() == null) {
            setSchedules(new ArrayList<Schedule>());
        }

        getSchedules().add(schedule);
    }

    public void removeSchedule(Schedule schedule) {
        if (schedule == null || getSchedules() == null) {
            return;
        }

        getSchedules().remove(schedule);
    }

    public void addChapter(Chapter chapter) {
        if (chapter == null) {
            return;
        }

        if (getChapters() == null) {
            setChapters(new ArrayList<Chapter>());
        }

        getChapters().add(chapter);
    }

    public void removeChapter(Chapter chapter) {
        if (chapter == null || getChapters() == null) {
            return;
        }

        getChapters().remove(chapter);
    }
}
