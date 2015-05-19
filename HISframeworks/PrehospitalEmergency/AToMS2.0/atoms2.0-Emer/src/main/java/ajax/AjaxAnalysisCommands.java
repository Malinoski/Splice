/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package ajax;

import org.lncc.martin.atoms.emer.ParamedicController;

/**
 *
 * @author iuri
 */
public class AjaxAnalysisCommands {
     public String searchAnalysisResponse(String idAnalysis){
        return new ParamedicController().searchAnalysisResponse(idAnalysis);
    }
}
