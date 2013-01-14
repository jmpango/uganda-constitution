/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uganda.constitution.api.service;

import org.uganda.constitution.api.model.Act;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ActService {

    void save(Act act) throws ValidationException;
}
