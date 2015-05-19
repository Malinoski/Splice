/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
/**
 *
 * @author iuri
 */

package teste;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;

public class PropertyUtils {
    
    public static void main(String args[]){
        System.out.println("RESULT:"+ new PropertyUtils().getPropertyValue("paramedicObservationTemplateFileName"));
    }

    public String getPropertyValue(String property) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            
            //input = new PropertyUtils().getClass().getResourceAsStream("config.properties");
//            System.out.println(">>>>>>."+PropertyUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            input = new FileInputStream("src/main/resources/config/config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(property);
    }
}
