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
 * Represents a chapter in a constitution.
 *
 * @author Jonathan
 */

@Entity
@Table(name = "chapter")
public class Chapter extends BaseData{
    private int chapterNumber;
    private String chapterTheme;
    private Constitution constitution;
    private List<Article> articles;
    private List<Chapter> subChapters;

    public Chapter(){}

    @OneToMany(mappedBy = "chapter", cascade = { CascadeType.ALL })
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Column(name = "chapter_number", nullable = true)
    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @Column(name = "chapter_theme", nullable = true)
    public String getChapterTheme() {
        return chapterTheme;
    }

    public void setChapterTheme(String chapterTheme) {
        this.chapterTheme = chapterTheme;
    }

    @ManyToOne()
    @JoinColumn(name = "constitution_id", nullable = true)
    public Constitution getConstitution() {
        return constitution;
    }

    public void setConstitution(Constitution constitution) {
        this.constitution = constitution;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_chapter", joinColumns = @JoinColumn(name = "chapter_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_chapter_id"))
   public List<Chapter> getSubChapters() {
        return subChapters;
    }

    public void setSubChapters(List<Chapter> subChapters) {
        this.subChapters = subChapters;
    }
}
