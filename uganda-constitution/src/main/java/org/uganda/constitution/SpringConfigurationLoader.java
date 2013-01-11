package org.uganda.constitution;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jonathan
 */
public class SpringConfigurationLoader {
    private static SpringConfigurationLoader configurationLoader;

    private SpringConfigurationLoader(){
         new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    public static SpringConfigurationLoader getConfigurationLoader() {
        if(configurationLoader == null){
            configurationLoader = new SpringConfigurationLoader();
        }
        return configurationLoader;
    }

}
