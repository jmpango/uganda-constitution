package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.Chapter;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ChapterService {
    void save(Chapter chapter) throws ValidationException;
    void delete(Chapter chapter) throws ValidationException;
    void validateChapter(Chapter chapter) throws ValidationException;
    Chapter getChapterById(String id);
    List<Chapter> getChapters();
}
