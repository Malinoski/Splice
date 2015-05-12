/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package br.martin.splice;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;

public class PathGetter {
	
	public String getArchetypePath(String archetypeId, String nodeId){
		
        String archetypePath;
        archetypePath = "/content[" + archetypeId + "]" + (loadArchetype(archetypeId)).getPathByNodeId(nodeId) + "/value";
        
        return archetypePath;
    }
	
	public String getParameters(String archetypeId, String nodeId){
		String retorno = "archetypeId = " + archetypeId + "\n nodeId = " + nodeId;
		
		return retorno;
	}
	
	public String getFileText(String archetypeId, String nodeId){
		String fileName = archetypeId + ".adl";
        String filePath = "archetypes/" + fileName;
        
        File file = new File(filePath);
        
        return file.getAbsolutePath();
	}
    
    private Archetype loadArchetype(String archetypeId){
        
        String fileName = archetypeId + ".adl";
        String filePath = "archetypes/" + fileName;
        
        File file = new File(filePath);
        ADLParser parser = null;
        
        try {
            parser = new ADLParser(file);
        } catch (IOException ex) {
            Logger.getLogger(PathGetter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problemas ao abrir o arquetipo");
        }
        
        Archetype archetype = null;
        try {
            archetype = parser.parse();
        } catch (Exception ex) {
            Logger.getLogger(PathGetter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problemas ao parsear arquetipo");
        }
        
        return archetype;
    }

}
