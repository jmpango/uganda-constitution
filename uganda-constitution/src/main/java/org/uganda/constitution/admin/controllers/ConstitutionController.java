package org.uganda.constitution.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uganda.constitution.api.model.Constitution;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ConstitutionService;

/**
 * @author Jonathan
 */

@Service("constitutionController")
public class ConstitutionController {

    @Autowired
    private ConstitutionService constitutionService;

    public ConstitutionController(){}

    public void saveConstitution(Constitution constitution) throws ValidationException{
           constitutionService.save(constitution);
    }
}
