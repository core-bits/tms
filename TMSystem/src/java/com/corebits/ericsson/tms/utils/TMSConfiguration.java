
package com.corebits.ericsson.tms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xtphere
 */
public class TMSConfiguration {
    
    private static Properties properties = new Properties();
    
    private TMSConfiguration(){
        try(InputStream stream = TMSConfiguration.class.getResourceAsStream("setting.properties")){
            
            if(Objects.nonNull(stream)){
                System.out.println("stream: ->>>>>> " + stream);
                properties.load(stream);
            }
        } catch (IOException ex) {
            Logger.getLogger(TMSConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getValue(String key){
        return properties.getProperty(key);
    }
    
    public String getValue(String key, String defaultValue){
        return properties.getProperty(key, defaultValue);
    }
    
    public static TMSConfiguration getInstance(){
        return TMSConfigurationHolder.tmsConfiguration;
    }
    
    private static final class TMSConfigurationHolder{
        public static final TMSConfiguration tmsConfiguration = new TMSConfiguration();
    }
}
