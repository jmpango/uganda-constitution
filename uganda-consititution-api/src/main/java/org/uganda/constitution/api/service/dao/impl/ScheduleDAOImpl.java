package org.uganda.constitution.api.service.dao.impl;

import org.springframework.stereotype.Repository;
import org.uganda.constitution.api.service.dao.ScheduleDAO;
import org.uganda.constitution.api.model.Schedule;

/**
 * @author Jonathan
 */
@Repository("scheduleDAO")
public class ScheduleDAOImpl extends BaseDAOImpl<Schedule> implements ScheduleDAO {

}
