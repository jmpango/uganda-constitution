package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.service.dao.ConstitutionDAO;
import org.uganda.constitution.api.model.Constitution;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ConstitutionService;

/**
 * @author Jonathan
 */
@Service("constitutionService")
@Transactional
public class ConstitutionServiceImpl implements ConstitutionService {

    @Autowired
    private ConstitutionDAO constitutionDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Constitution constition) throws ValidationException {
        constitutionDAO.save(constition);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Constitution constition) throws ValidationException {
        constitutionDAO.delete(constition);
    }

    @Override
    public void validateConstitution(Constitution constitution) throws ValidationException {
        if (constitution == null) {
            throw new ValidationException("supplied constitution is null");
        }

        if (StringUtils.isEmpty(constitution.getName())) {
            throw new ValidationException("supplied constitution is missing name");
        }

        if (StringUtils.isEmpty(constitution.getLanguage())) {
            throw new ValidationException("supplied constitution is missing language");
        }

        if (constitution.getYear() <= 0) {
            throw new ValidationException("supplied constitution year is invalid");
        }
    }

    @Override
    @Transactional(readOnly=true)
    public Constitution getConstitution(String id) {
        return constitutionDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly=true)
    public Constitution getConstitutionByYear(int year) {
        return constitutionDAO.searchUniqueByPropertyEqual("year", year);
    }

    @Override
    @Transactional(readOnly=true)
    public Constitution getConstitutionByLanguage(String language) {
        return constitutionDAO.searchUniqueByPropertyEqual("language", language);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Constitution> getConstitutions() {
        return constitutionDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }
}