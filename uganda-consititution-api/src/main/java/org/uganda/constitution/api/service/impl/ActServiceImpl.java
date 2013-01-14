package org.uganda.constitution.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.Act;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ActService;
import org.uganda.constitution.api.service.dao.ActDAO;

/**
 * @author Jonathan
 */
@Service("actService")
@Transactional
public class ActServiceImpl implements ActService {

    @Autowired
    private ActDAO actDAO;

    @Override
    public void save(Act act) throws ValidationException {
        actDAO.save(act);
    }
}
