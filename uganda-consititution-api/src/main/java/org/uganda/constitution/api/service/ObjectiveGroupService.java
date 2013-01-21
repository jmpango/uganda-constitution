package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.ObjectiveGroup;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ObjectiveGroupService {
    void save(ObjectiveGroup objGroup) throws ValidationException;
    void delete(ObjectiveGroup objGroup) throws ValidationException;
    void validateObjectiveGroup(ObjectiveGroup objGroup) throws ValidationException;
    ObjectiveGroup getObjectiveGroup(String id);
    List<ObjectiveGroup> getObjectiveGroups();
}
