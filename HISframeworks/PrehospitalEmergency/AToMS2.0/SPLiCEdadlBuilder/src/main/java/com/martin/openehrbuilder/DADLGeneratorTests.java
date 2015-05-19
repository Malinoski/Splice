/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package com.martin.openehrbuilder;

import CONTROL.ArchetypeController;
import com.martin.spliceexistdb.ExistdbDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iuri
 */
public class DADLGeneratorTests {

    public static void main(String[] args) throws Exception {
        new DADLGeneratorTests().generateObservationLibVinicius();

//            new DADLGeneratorTests().generateXmlInstanceTestObservation2();
//            new DADLGeneratorTests().testInstruction();
//            new DADLGeneratorTests().generateMapFromXMLTest2();
//            new DADLGeneratorTests().generateMapFromXMLTest();
//            new DADLGeneratorTests().generateXmlInstanceTestInstruction();
//        System.out.println(">>\n" + xml);
//        DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");
//        Map<String, String[]> map = gen.generateMapFromXML(xml);
//        System.out.println("map: instruc! \n" + map);
//        String xml = new ExistdbDAO().get("97---9-4-2014--19-56-54.xml");
//        System.out.println(">>\n" + xml); 
//        DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");
//        Map<String, String[]> map = gen.generateMapFromXML(xml);
//        System.out.println("map: instruc! \n" + map);
        /////////////////////////////////////////////////////////////////////
        /// TESTES - DVDQUANTITY (obs.: arquetipos de instrucao nao possuem dvquantity)
        /////////////////////////////////////////////////////////////////////
//        try {
//            DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation");
//
//            Map<String, String[]> map = new HashMap<>();
//
//            String[] arg1 = {"66.6", "LIXO1"};
//            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value", arg1);
//            String[] arg2 = {"66.7", "LIXO2"};
//            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value", arg2);
//            String[] arg3 = {"66.8", "LIXO3"};
//            map.put("/content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value", arg3);
//            String xml = gen.generateXmlInstance(map);
//            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String xml = new ExistdbDAO().get("oie123.xml");
//        System.out.println(">>\n" + xml);
//        DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");
//        Map<String, String[]> map = gen.generateMapFromXML(xml);
//        System.out.println("map: instruc! \n" + map);
//        /////////////////////////////// TESTANDO INSTRUCAO
//            try {
////            DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");
//
////            Map<String, String[]> map = new HashMap<>();
////
////            String[] arg1 = {"67.6", "LIXO1"};
////            map.put("PATH!!", arg1);
////            String xml = gen.generateXmlInstance(map);
////            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public void generateXmlInstanceTestObservation() {
        ////        /////////test1
        try {
            DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation");

            Map<String, String[]> map = new HashMap<>();

            String[] arg1 = {"12.9", "LIXO1"};
            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value", arg1);
            String[] arg2 = {"8.2", "LIXO2"};
            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value", arg2);
            String[] arg3 = {"50.3", "LIXO3"};
            map.put("/content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value", arg3);
            String xml = gen.generateXmlInstance(map);
            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        /content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value
//        /content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value
//        /content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value
//        /content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value
//        /content[openEHR-EHR-OBSERVATION.ecg.v1]/data[at0001]/events[at0002]/data[at0005]/items[at0081]/value
//        /content[openEHR-EHR-OBSERVATION.ecg.v1]/data[at0001]/events[at0002]/data[at0005]/items[at0010]/value
    }

    //ECG!!!!!
    public void generateXmlInstanceTestObservation2() {

        ////        /////////test1
        try {
            DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation-v2");

            Map<String, String[]> map = new HashMap<>();

            String[] arg1 = {"12.9"}; //DVQUANTITY
            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value", arg1);
            String[] arg2 = {"8.2"}; //DVQUANTITY
            map.put("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value", arg2);
            String[] arg3 = {"50.3"}; //DVQUANTITY
            map.put("/content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value", arg3);
            String[] arg4 = {"at0012CODECODE"}; //DVCODEDTEXT (at0012 é uma das opcoes)
            map.put("/content[openEHR-EHR-OBSERVATION.respiration.v1]/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value", arg4);
            String[] arg5 = {"alguma coisa"}; //DVTEXT
            map.put("/content[openEHR-EHR-OBSERVATION.ecg.v1]/data[at0001]/events[at0002]/data[at0005]/items[at0081]/value", arg5);
            String[] arg6 = {"www.gmail.com.br/blahblah"}; //DV_MULTIMEDIA
            map.put("/content[openEHR-EHR-OBSERVATION.ecg.v1]/data[at0001]/events[at0002]/data[at0005]/items[at0010]/value", arg6);

            String xml = gen.generateXmlInstance(map);
            System.out.println("XML >>>'>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);

            HashMap<String, String[]> mult = new HashMap<String, String[]>();
            String url[] = {"URLLLLLLLLLLL"};
            mult.put("blah", url);
            xml = update(xml, mult);
            System.out.println("XML2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);

            new ExistdbDAO().putDocumentResource(xml, "testeEcg.xml");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void generateMapFromXMLTest2() throws Exception {

        //String xml = new ExistdbDAO().get("14---20-3-2014--15-02-50.xml");
        String xml = new ExistdbDAO().getDocumentResource("testeEcg.xml");

        System.out.println(">>\n" + xml);
        DADLGenerator gen = new DADLGenerator("TemapleParamedicObservation-v2");
        Map<String, String[]> map = gen.generateMapFromXML(xml);
//        System.out.println("map: \n"+map);
        for (Map.Entry<String, String[]> mapEntry : map.entrySet()) {
            System.out.println("# KEY: " + mapEntry.getKey());
//            System.out.println("**VAL*"+mapEntry.getValue());
            System.out.print("# VAL: ");
            for (int i = 0; i < mapEntry.getValue().length; i++) {
                System.out.print(mapEntry.getValue()[i] + ", ");
            }
            System.out.println();
        }

    }

    private void generateMapFromXMLTest() throws Exception {

        //String xml = new ExistdbDAO().get("14---20-3-2014--15-02-50.xml");
        String xml = new ExistdbDAO().getDocumentResource("oie123.xml");

        System.out.println(">>\n" + xml);
        DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");
        Map<String, String[]> map = gen.generateMapFromXML(xml);
//        System.out.println("map: \n"+map);
        for (Map.Entry<String, String[]> mapEntry : map.entrySet()) {
            System.out.println("**KEY*" + mapEntry.getKey());
            System.out.println("**VAL*" + mapEntry.getValue().toString());
        }

    }

    public void generateXmlInstanceTestInstruction() {
        //      //test3
        try {
            DADLGenerator gen = new DADLGenerator("TemplateSpecialistInstruction");

            Map<String, String[]> map = new HashMap<>();

            String[] arg1 = {"teXtoat0009"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0009]/value", arg1);
            String[] arg2 = {"teXtoat0018"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0018]/value", arg2);
            String[] arg3 = {"teXtoat0035"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0035]/value", arg3);
            String[] arg4 = {"teXtoat0121"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0121]/value", arg3);
            String[] arg5 = {"teXtoat0135"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0135]/value", arg3);
            String xml = gen.generateXmlInstance(map);
            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);
//            new ExistdbDAO().putXMLResource(xml,"oie123.xml");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void testInstruction() {
        try {
            DADLGenerator gen = new DADLGenerator("prescricao");
//            gen.printPathNodeMap();

            Map<String, String[]> map = new HashMap<>();
//
            String[] arg1 = {"teXtoat0009"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0003]/value", arg1);
            String[] arg2 = {"teXtoat0018"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0009]/value", arg2);
            String[] arg3 = {"teXtoat0035"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0005]/value", arg3);
            String[] arg4 = {"teXtoat0121"};
            map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0008]/value", arg4);
            String[] arg5 = {"teXtoat0135"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request-lab_test.v1]/activities[at0001]/description[at0009]/items[at0121]/value", arg5);
            String[] arg6 = {"teXtoat0139"};
            map.put("/content[openEHR-EHR-INSTRUCTION.request-lab_test.v1]/activities[at0001]/description[at0009]/items[at0135]/value", arg6);
            String xml = gen.generateXmlInstance(map);
            System.out.println("XML >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + xml);

            Map<String, String[]> mapReceived = new HashMap<>();
            mapReceived = new DADLGenerator("prescricao").generateMapFromXML(xml);
            System.out.println("MAP >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + mapReceived);
            for (Map.Entry<String, String[]> mapEntry : mapReceived.entrySet()) {
                System.out.println("Key  : " + mapEntry.getKey());
                System.out.println("Value: " + mapEntry.getValue());
            }

//            new ExistdbDAO().putXMLResource(xml,"oie123.xml");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String update(String ehr, HashMap<String, String[]> mult) {

        //TODO - Aqui entra o metodo do vinicius para dadl (nao xml)
        //////////////////////////////////
        ////////////////////////TEMPORARIO - super gambiarra contra o tempo!
        //////////////////////////////////        
        for (Map.Entry<String, String[]> mapEntry : mult.entrySet()) {
            ehr = ehr.replace("www.iana.org", mapEntry.getValue()[0]);
        }

        return ehr;
    }

    private void generateObservationLibVinicius() {
        ArchetypeController ac = new ArchetypeController(
                "/home/iuri/Dropbox/SPLICE/archetypes_2014_03_26-17_23_42",
                "/home/iuri/Dropbox/SPLICE/templates/");

        Map<String, String[]> map = new HashMap<>();
//
        String[] arg1 = {"teXtoat0009"};
        map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0003]/value", arg1);
        String[] arg2 = {"teXtoat0018"};
        map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0009]/value", arg2);
        String[] arg3 = {"teXtoat0035"};
        map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0005]/value", arg3);
        String[] arg4 = {"teXtoat0121"};
        map.put("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0008]/value", arg4);
        String[] arg5 = {"teXtoat0135"};
        map.put("/content[openEHR-EHR-INSTRUCTION.request-lab_test.v1]/activities[at0001]/description[at0009]/items[at0121]/value", arg5);
        String[] arg6 = {"teXtoat0139"};
        map.put("/content[openEHR-EHR-INSTRUCTION.request-lab_test.v1]/activities[at0001]/description[at0009]/items[at0135]/value", arg6);
        String dadl = ac.generateEHRInstance("prescricao", map);
        System.out.println("DADL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + dadl);
        
        try {
            new ExistdbDAO().putDocumentResource(dadl, "finegame.dadl");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
//        Map<String, String[]> map2 = new HashMap<>();
//        map2 = ac.generateMapFromEHR(dadl, "prescricao");
//        ac.printDataMap(map2);
        
        

    }
}
