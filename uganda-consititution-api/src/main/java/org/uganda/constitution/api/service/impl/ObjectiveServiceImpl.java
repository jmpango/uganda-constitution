package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.Objective;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ObjectiveService;
import org.uganda.constitution.api.service.dao.ObjectiveDAO;

/**
 *
 * @author Jonathan
 */
@Service("objectiveService")
@Transactional
public class ObjectiveServiceImpl implements ObjectiveService{

    @Autowired
    private ObjectiveDAO objectiveDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Objective objective) throws ValidationException {
        objectiveDAO.save(objective);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Objective objective) throws ValidationException {
       objectiveDAO.delete(objective);
    }

    @Override
    public void validateObjective(Objective objective) throws ValidationException {
        if (objective == null) {
            throw new ValidationException("supplied objective is null");
        }
        if (StringUtils.isEmpty(objective.getObjectiveNumber())) {
            throw new ValidationException("supplied objective is missing number");
        }

    }

    @Override
    @Transactional(readOnly=true)
    public Objective getObjective(String id) {
       return objectiveDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Objective> getObjective() {
        return objectiveDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }

}
