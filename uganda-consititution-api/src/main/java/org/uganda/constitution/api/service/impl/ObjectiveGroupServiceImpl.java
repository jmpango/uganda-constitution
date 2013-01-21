/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.ObjectiveGroup;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ObjectiveGroupService;
import org.uganda.constitution.api.service.dao.ObjectiveGroupDAO;

/**
 *
 * @author Jonathan
 */
@Service("objectiveGroupService")
@Transactional
public class ObjectiveGroupServiceImpl implements ObjectiveGroupService {

    @Autowired
    private ObjectiveGroupDAO objectiveGroupDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(ObjectiveGroup objGroup) throws ValidationException {
        objectiveGroupDAO.save(objGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(ObjectiveGroup objGroup) throws ValidationException {
        objectiveGroupDAO.delete(objGroup);
    }

    @Override
    public void validateObjectiveGroup(ObjectiveGroup objGroup) throws ValidationException {
        if (objGroup == null) {
            throw new ValidationException("supplied objectiveGroup is null");
        }

        if (StringUtils.isEmpty(objGroup.getName())) {
            throw new ValidationException("supplied objectiveGroup is missing name");
        }
        
        if (objGroup.getObjGroupNumber() <= 0) {
            throw new ValidationException("supplied objectiveGroup Number is invalid");
        }
    }

    @Override
    @Transactional(readOnly=true)
    public ObjectiveGroup getObjectiveGroup(String id) {
        return objectiveGroupDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<ObjectiveGroup> getObjectiveGroups() {
       return objectiveGroupDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }
}
