package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.Clause;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ClauseService;
import org.uganda.constitution.api.service.dao.ClauseDAO;

/**
 *
 * @author Jonathan
 */
@Service("clauseService")
@Transactional
public class ClauseServiceImpl implements ClauseService {

    @Autowired
    private ClauseDAO clauseDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Clause clause) throws ValidationException {
        clauseDAO.save(clause);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Clause clause) throws ValidationException {
        clauseDAO.delete(clause);
    }

    @Override
    public void validateClause(Clause clause) throws ValidationException {
        if (clause == null) {
            throw new ValidationException("supplied clause is null");
        }

        if (clause.getClauseNumber() <= 0) {
            throw new ValidationException("supplied clause clauseNumber is invalid");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Clause getClauseById(String id) {
        return clauseDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Clause> getClauses() {
        return clauseDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }
}
