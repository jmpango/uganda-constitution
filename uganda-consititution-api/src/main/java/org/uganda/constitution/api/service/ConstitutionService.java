package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.Constitution;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 * @author Jonathan
 */
public interface ConstitutionService {
    /**
     * Saves a constitution.
     * @param constition
     * @throws ValidationException
     */
    void save(Constitution constition) throws ValidationException;

    /**
     * Deletes a constitution.
     * @param constition
     * @throws ValidationException
     */
    void delete(Constitution constition) throws ValidationException;

    /**
     * Validates a constitution before saving.
     * @param constitution
     * @throws ValidationException
     */
    void validateConstitution(Constitution constitution) throws ValidationException;

    /**
     * Gets a constitution based on constitution id.
     * @param id
     * @return
     */
    Constitution getConstitution(String id);

    /**
     * Gets a constitution based on constitution year.
     * @param year
     * @return
     */
    Constitution getConstitutionByYear(int year);

    /**
     * Gets a constitution based on constitution language.
     * @param language
     * @return
     */
    Constitution getConstitutionByLanguage(String language);

    /**
     * Gets  a list of constitions.
     * @return
     */
    List<Constitution> getConstitutions();
    

}
