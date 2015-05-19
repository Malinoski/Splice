/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openehr.rm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iuri
 */
public class SPLiCEUtils {
    
    public List<String> getListFilesWithoutExtensionFromFolder(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                List<String> subFiles = getListFilesWithoutExtensionFromFolder(fileEntry);
                files.addAll(subFiles);
            } else {
                String fileNameWithoutExtension = fileEntry.getName().substring(0,fileEntry.getName().lastIndexOf("."));
                System.out.println(fileNameWithoutExtension);
                files.add(fileNameWithoutExtension);                
            }            
        }
        return files;
    }
    
    public String[] getVectorFilesWithoutExtensionFromFolder(final File folder) {
        List<String> files = new SPLiCEUtils().getListFilesWithoutExtensionFromFolder(folder);
        String [] vectorStringFiles = new String[files.size()];
        for(int i = 0; i< files.size(); i++){
            vectorStringFiles[i] = files.get(i);
        }
        return vectorStringFiles;
    }
    
}
