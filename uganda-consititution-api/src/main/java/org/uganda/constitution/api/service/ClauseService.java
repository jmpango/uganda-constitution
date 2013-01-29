package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.Clause;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ClauseService {
    void save(Clause clause) throws ValidationException;
    void delete(Clause clause) throws ValidationException;
    void validateClause(Clause clause) throws ValidationException;
    Clause getClauseById(String id);
    List<Clause> getClauses();
}
