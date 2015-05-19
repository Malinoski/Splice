/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.atoms.emer.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Iuri
 */
public class OpenEHRUtils {
    
     public static Map convertJspEhrDataToMap(Map <String, String[]> params) {
        //transformacao dos dados da pagina para map
         
        System.out.println("params: " + params);
        Iterator i = params.keySet().iterator();
        System.out.println("i: " + i);
        
        //transformacao dos dados da pagina para map
        Hashtable<String, String> map = new Hashtable<String,String>();
        System.out.println("## emer ");
        while (i.hasNext()) {
            String key = (String) i.next();
            String value = (String) params.get(key)[0];            
            System.out.println("## " + key + "=" + value);
            //OpenEHRDataKeyValue data = new OpenEHRDataKeyValue();
            map.put(key, value);
        }
        return map;
    }

    
}
