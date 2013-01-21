package org.uganda.constitution.api.springbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.uganda.constitution.api.service.ConstitutionService;
import org.uganda.constitution.api.service.ObjectiveGroupService;

/**
 * Handles all the beans autowired by spring.
 *
 * @author Jonathan
 */
public class ApplicationSpringBeans {
    private static ApplicationSpringBeans _applicationSpringBeans;
    private static ApplicationContext _appContext;

    public static ApplicationSpringBeans getApplicationSpringBeans() {
        if(_applicationSpringBeans == null){
            _applicationSpringBeans = new ApplicationSpringBeans();
        }
        return _applicationSpringBeans;
    }
    
    private ApplicationSpringBeans(){
        _appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    public static ConstitutionService getConstitutionService(){
        return  (ConstitutionService) _appContext.getBean("constitutionService");
    }
    public static ObjectiveGroupService getObjectiveGroupService(){
        return  (ObjectiveGroupService) _appContext.getBean("objectiveGroupService");
    }
}
