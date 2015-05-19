/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.atoms.esp;

import java.util.HashMap;
import org.lncc.martin.ct.ws.AnalysisModel;

public class MessageAnalysisRequest extends AnalysisModel{

    private HashMap<String,String[]> EHRmap;
    
    public HashMap<String,String[]> getEHRmap() {
        return EHRmap;
    }

    /**
     * @param EHRmap the EHRmap to set
     */
    public void setEHRmap(HashMap<String,String[]> EHRmap) {
        this.EHRmap = EHRmap;
    }
}
