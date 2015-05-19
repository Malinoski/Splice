/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.ct.ws;

import java.util.Map;

/**
 *
 * @author Iuri
 */
public class ClinicalFileModel {
    
    private String fileName;
    private String fileContent;
    
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileContent
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }    
}
