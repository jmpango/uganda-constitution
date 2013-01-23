package org.uganda.constitution.api.service.impl;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.uganda.constitution.api.model.RecordStatus;
import org.uganda.constitution.api.model.Schedule;
import org.uganda.constitution.api.model.exception.ValidationException;
import org.uganda.constitution.api.service.ScheduleService;
import org.uganda.constitution.api.service.dao.ScheduleDAO;

/**
 *
 * @author Jonathan
 */
@Service("scheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Schedule schedule) throws ValidationException {
        scheduleDAO.save(schedule);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Schedule schedule) throws ValidationException {
        scheduleDAO.delete(schedule);
    }

    @Override
    public void validateSchedule(Schedule schedule) throws ValidationException {
        if (schedule == null) {
            throw new ValidationException("supplied schedule is null");
        }

        if (schedule.getSchedule_number() <= 0) {
            throw new ValidationException("supplied schedule number is invalid");
        }
                
        if (StringUtils.isEmpty(schedule.getSchedule_title())) {
            throw new ValidationException("supplied schedule title is missing");
        }
        
        if (StringUtils.isEmpty(schedule.getTextContent())) {
            throw new ValidationException("supplied schedule text content is missing");
        }


    }

    @Override
    @Transactional(readOnly=true)
    public Schedule getSchedule(String id) {
        return scheduleDAO.searchUniqueByPropertyEqual("id", id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Schedule> getSchedules() {
        return scheduleDAO.searchByRecordStatus(RecordStatus.ACTIVE);
    }

}
