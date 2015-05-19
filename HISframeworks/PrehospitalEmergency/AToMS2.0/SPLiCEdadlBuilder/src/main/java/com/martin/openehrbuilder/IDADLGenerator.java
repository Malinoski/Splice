/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package com.martin.openehrbuilder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Iuri
 */
public interface IDADLGenerator {
    
    public HashMap<String, String[]> generateMapFromXML(String xml);
    public String generateXmlInstance(Map<String, String[]> dataMap) throws Exception;
    public String update(String ehr, HashMap<String, String[]> multiMediaListUri);
    
}
