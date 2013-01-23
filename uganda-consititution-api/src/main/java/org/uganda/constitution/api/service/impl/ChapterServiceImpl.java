package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.Chapter;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ChapterService;
import org.uganda.constitution.api.service.dao.ChapterDAO;

/**
 *
 * @author Jonathan
 */
@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {
    
    @Autowired
    private ChapterDAO chapterDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Chapter chapter) throws ValidationException {
        chapterDAO.save(chapter);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Chapter chapter) throws ValidationException {
        chapterDAO.delete(chapter);
    }

    @Override
    public void validateChapter(Chapter chapter) throws ValidationException {
        if (chapter == null) {
            throw new ValidationException("supplied chapter is null");
        }

        if (chapter.getChapterNumber() <= 0) {
            throw new ValidationException("supplied chapter number is invalid");
        }

         if (StringUtils.isEmpty(chapter.getChapterTheme())) {
            throw new ValidationException("supplied chapter theme is empty");
        }
    }

    @Override
    @Transactional(readOnly=true)
    public Chapter getChapterById(String id) {
        return chapterDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Chapter> getChapters() {
       return chapterDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }

}
