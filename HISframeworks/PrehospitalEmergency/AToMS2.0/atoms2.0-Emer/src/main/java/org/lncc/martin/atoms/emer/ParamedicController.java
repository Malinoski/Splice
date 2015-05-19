/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.atoms.emer;

import CONTROL.ArchetypeController;
import com.martin.openehrbuilder.DADLGenerator;
import com.martin.openehrbuilder.IDADLGenerator;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import teste.PropertyUtils;
import org.lncc.martin.ct.ws.AnalysisModel;
import org.lncc.martin.ct.ws.ClinicalFileModel;
import org.lncc.martin.ct.ws.MultiMedia;

/**
 *
 * @author Iuri
 */
public class ParamedicController {
    
    private ArchetypeController ac;
    
    public ParamedicController(){
        
        ac = new ArchetypeController(
                "/home/iuri/Dropbox/SPLICE/archetypes_2014_03_26-17_23_42",
                "/home/iuri/Dropbox/SPLICE/templates/");
    }

    public long sendAnalysisRequest(Map params, long idEmergencista, HashMap<String, byte[]> mediaParams) {
        System.out.println("# sendAnalysisRequest");
        String ehrContentFile = generateXMLfromMap(params);
        if(ehrContentFile == null) return -1;// -1 significa erro
        
        long result = -1;
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            

            //Modelo de dado clínico
            ClinicalFileModel cModel = new ClinicalFileModel();
            cModel.setFileContent(ehrContentFile);
            AnalysisModel aModel = new AnalysisModel();                 
            aModel.setObservation(cModel);
            aModel.setIdEmergencista(idEmergencista);
            
            //Multimedia - Convertendo HashMap (tipo complexo do cliente) para MultiMedia (tipo chave-valor simplificado do servidor)
            List<MultiMedia> multiMediaServerList = new ArrayList<MultiMedia>();
            for (Map.Entry<String, byte[]> mapEntry : mediaParams.entrySet()) {
                MultiMedia m = new MultiMedia();
                m.setPath(mapEntry.getKey());
                m.setBytes(mapEntry.getValue());
                multiMediaServerList.add(m);
            }            
            
            result = port.sendAnalysisRequest(aModel,multiMediaServerList);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            Logger.getLogger(ParamedicController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }
    
    public String searchAnalysisResponse(String idAnalysis){
        System.out.println("# ParamedicControllerd().searchAnalysisResponse(idAnalysis="+idAnalysis+")");
        long id = Long.parseLong(idAnalysis);
        
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            // TODO process result here
            java.lang.String result = port.searchAnalysisResponse(id);
            System.out.println("Result = "+result);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return "";
    }
    
    public String getAnalysisResponse(long idAnalysis){
        
        java.lang.String result = null;
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            result = port.getXMLAnalysisResponse(idAnalysis);
            System.out.println("# Getting analysis (id="+idAnalysis+")response xml file name: "+result);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO handle custom exceptions here
        }        
        return null;
    }

    private String generateXMLfromMap(Map params){
        String ehr = null;
        try {
            //DADLGenerator gen = new DADLGenerator("template2");
            //IDADLGenerator gen = new FakeDADLGenerator(FakeDADLGenerator.TYPE.OBS);
            //IDADLGenerator gen = new DADLGenerator(new PropertyUtils().getPropertyValue("paramedicTemplateFileName"));
//            IDADLGenerator gen = new DADLGenerator("TemapleParamedicObservation-v2");
//            xml = gen.generateXmlInstance(params);
            
            ehr = ac.generateEHRInstance("TemapleParamedicObservation-v2", params);

        } catch (Exception ex) {            
            Logger.getLogger(ParamedicController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ehr;
    }

    ///REMOVER!!!!!!!!!!!!!!!
    public HashMap<String,String[]> getMapAnalysisResponse(long idAnalysis) {
        System.out.println("# receiveAnalysisRequest");
        
        HashMap<String, String[]> map = new HashMap<String, String[]>();

        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();

            String xml = port.getXMLAnalysisResponse(idAnalysis);
            
            System.out.print("Chamada WS getXMLAnalysisResponse(idAnalysis=" + idAnalysis + ")");
            if (xml == null) {
                System.out.println("FALHOU!");
            } else {
                System.out.println("FUNCIONOU!");
            }

            //DADLGenerator gen = new DADLGenerator("template2");
//            IDADLGenerator gen = new FakeDADLGenerator(FakeDADLGenerator.TYPE.INST);

//            map = gen.generateMapFromXML(xml);
//            System.out.println("## ESP Controller (map)\n:" + map);

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        
        return map;
    }

    public MessageAnalysisRequest receiveAnalysisResponse(long idAnalysis) {
        MessageAnalysisRequest message = null;
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            AnalysisModel aModel = port.getAnalysisResponse(idAnalysis);
            
            message = new MessageAnalysisRequest();
            message.setDataCadastro(aModel.getDataCadastro());
            message.setId(aModel.getId());
            message.setIdEmergencista(aModel.getIdEmergencista());
            message.setIdEspecialista(aModel.getIdEspecialista());
            message.setInstruction(aModel.getInstruction());
            message.setObservation(aModel.getObservation());            
            
            String xml = aModel.getInstruction().getFileContent();
            //Iuri
//            DADLGenerator gen = new DADLGenerator("prescricao");
//            map = gen.generateMapFromXML(xml);
            //vinicius
            map = ac.generateMapFromEHR(xml, "prescricao");
            message.setEHRmap(map);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    public boolean finishAnalysis(long analysisId) {
        boolean result = false;
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            result = port.finishAnalysis(analysisId);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return result;

    }
    
    
}
