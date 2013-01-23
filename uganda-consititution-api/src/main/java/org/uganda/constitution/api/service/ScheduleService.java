package org.uganda.constitution.api.service;

import java.util.List;
import org.uganda.constitution.api.model.Schedule;
import org.uganda.constitution.api.model.exception.ValidationException;

/**
 *
 * @author Jonathan
 */
public interface ScheduleService {
    void save(Schedule schedule) throws ValidationException;
    void delete(Schedule schedule) throws ValidationException;
    void validateSchedule(Schedule schedule) throws ValidationException;
    Schedule getSchedule(String id);
    List<Schedule> getSchedules();
}
