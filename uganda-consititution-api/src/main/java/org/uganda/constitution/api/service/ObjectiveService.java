package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.Objective;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ObjectiveService {

    void save(Objective objective) throws ValidationException;

    void delete(Objective objective) throws ValidationException;

    void validateObjective(Objective objective) throws ValidationException;

    Objective getObjective(String id);

    List<Objective> getObjective();
}
