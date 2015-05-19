/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.atoms.esp;

import java.util.List;

public class AjaxSpecialistAnalysisRequest {

    public List getAllAnalysisRequestPending(long idEsp, long idArea) {        
        return new SpecialistController().getAllAnalysisRequestPending(idEsp, idArea);
    }
}
