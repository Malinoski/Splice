/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.atoms.esp;

import CONTROL.ArchetypeController;
import com.martin.openehrbuilder.DADLGenerator;
import com.martin.openehrbuilder.IDADLGenerator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lncc.martin.ct.ws.AnalysisModel;

/**
 *
 * @author Iuri
 */
public class SpecialistController {

    private ArchetypeController ac;

    public SpecialistController() {
        ac = new ArchetypeController(
                "/home/iuri/Dropbox/SPLICE/archetypes_2014_03_26-17_23_42",
                "/home/iuri/Dropbox/SPLICE/templates/");
    }

    public static void main(String args[]) {
//        HashMap<String, String[]> map = new HashMap<String, String[]>();
//        map = new SpecialistController().receiveAnalysisRequest("14---20-3-2014--15-02-50.xml");
//        System.out.println(">>::"+map);

        try {
            DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation-v2");

            Map<String, String[]> map = new HashMap<String, String[]>();

            String[] arg1 = {"algum texto"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0009]/value", arg1);
            String[] arg2 = {"algum teXto2"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0018]/value", arg2);
            String[] arg3 = {"algum teXXto3"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0035]/value", arg3);
            String[] arg4 = {"algum teXXto4"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0121]/value", arg3);
            String[] arg5 = {"algum teXXto5"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0135]/value", arg3);
            String xml = gen.generateXmlInstance(map);
            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List getAllAnalysisRequestPending(long idEsp, long idArea) {
        LinkedList messages = new LinkedList();
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();

            // TODO process result here
            java.util.List<org.lncc.martin.ct.ws.AnalysisModel> result = port.getAllAnalysisRequestPending(idEsp, idArea);
            System.out.println("#####getAllAnalysisRequestPending " + result);
            for (org.lncc.martin.ct.ws.AnalysisModel a : result) {
                System.out.println("* " + a.getObservation().getFileName());
                messages.addFirst(a);
            }

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return messages;
    }

    public MessageAnalysisRequest receiveAnalysisRequest(long idAnalysis, long idEsp) {
        System.out.println("# receiveAnalysisRequest!");

        MessageAnalysisRequest message = new MessageAnalysisRequest();
        HashMap<String, String[]> map = new HashMap<String, String[]>();

        //teste 1
//        String[] arg1 = {"9.9", "LIXO1"};
//        map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value", arg1);
//        String[] arg2 = {"8.8", "LIXO2"};
//        map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value", arg2);
//        String[] arg3 = {"7.7", "LIXO3"};
//        map.put("/content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value", arg3);
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            AnalysisModel aModel = new AnalysisModel();
            aModel = port.getAnalysisRequest(idAnalysis, idEsp);

            message.setDataCadastro(aModel.getDataCadastro());
            message.setId(aModel.getId());
            message.setIdEmergencista(aModel.getIdEmergencista());
            message.setIdEspecialista(aModel.getIdEspecialista());
            message.setInstruction(aModel.getInstruction());
            message.setObservation(aModel.getObservation());

            String xml = aModel.getObservation().getFileContent();
//            DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation-v2");
//            map = gen.generateMapFromXML(xml);
            map = ac.generateMapFromEHR(xml, "TemapleParamedicObservation-v2");
            message.setEHRmap(map);

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return message;
    }

    public boolean sendAnalysisResponse(long idAnalysis, Map params) {
        System.out.println("#ESP - sendAnalysisResponse()");
        String ehr = "";
        boolean result = false;
        try {

            //Iuri
//            IDADLGenerator gen = new DADLGenerator("prescricao");
//            ehr = gen.generateXmlInstance(params);
            //Vinicius
            ehr = ac.generateEHRInstance("prescricao", params);

            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ///
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            // TODO initialize WS operation arguments here

            // TODO process result here
            result = port.sendAnalysisResponse(idAnalysis, ehr);
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    byte[] getMultimedia(String fileName) {

        byte[] result = null;
        try { // Call Web Service Operation
            org.lncc.martin.ct.ws.AnalysisService_Service service = new org.lncc.martin.ct.ws.AnalysisService_Service();
            org.lncc.martin.ct.ws.AnalysisService port = service.getAnalysisServicePort();
            // TODO initialize WS operation arguments here

            // TODO process result here
            result = port.getMultimedia(fileName);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return result;
    }
}
