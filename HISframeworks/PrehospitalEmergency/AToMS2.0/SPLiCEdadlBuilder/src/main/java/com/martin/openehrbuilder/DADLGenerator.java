/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package com.martin.openehrbuilder;

import org.openehr.rm.util.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import static junit.framework.Assert.assertNotNull;

import openEHR.v1.template.TEMPLATE;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.schemas.v1.COMPOSITION;
import org.openehr.schemas.v1.CompositionDocument;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.CodeSetAccess;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.terminology.SimpleTerminologyService;
import se.acode.openehr.parser.ADLParser;

public class DADLGenerator extends SkeletonGeneratorTestBase implements IDADLGenerator {

    XmlOptions xmlOptions;
    OutputStream outputStream = null;
    Collection<String> collection;
    List<String> lines;
    TEMPLATE template;
    String xmlString;
    Archetype flattened;
    Composition comp;
    
    public DADLGenerator(String templateFileName) throws Exception {

        //new ExistdbDAO().put("oi", "oi");
        System.out.println("## DADLGenerator...");
        template = loadTemplate(templateFileName);

        flattened = flattenTemplate(template.getName());
        setXmlOptions();
        instance = generator.create(flattened, null, archetypeMap,
                GenerationStrategy.MAXIMUM);
        comp = (Composition) instance;

        //DEBUG TESTES
//        System.out.println("### getPathNodeMap" + flattened.getPathNodeMap());
//        System.out.println("#### itemAtPath"+comp.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]"));
        //System.out.println(">>>>"+composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]").getClass());
//        try{
//            if(composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]") instanceof Element){
//                Element e = (Element)composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]");
//                String rmName = e.getValue().getReferenceModelName();
//                if(rmName == "DV_QUANTITY"){
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+e.getValue().getReferenceModelName());
//                }else{
//                    System.out.println("FAIL");
//                }
//            }
//        }catch(Exception ex){
//            System.out.println("Exception");
//        }
        //
        //TESTE - Como descobrir o tipo (como DVQUANTITY, DvText, etc.) atraves de um path
//        System.out.println(">>>"+composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value").getClass());        
//        System.out.println(">>>" + composition.itemAtPath("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0009]/value").getClass());
//        System.out.println(">>>" + composition.itemAtPath("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0018]/value").getClass());
//        System.out.println(">>>" + composition.itemAtPath("/content[openEHR-EHR-INSTRUCTION.medication_order.v1]/activities[at0001]/description[at0002]/items[at0035]/value").getClass());
//        System.out.println(">>>" + composition.itemAtPath("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0121]/value").getClass());
//        System.out.println(">>>" + composition.itemAtPath("/content[openEHR-EHR-INSTRUCTION.request.v1]/activities[at0001]/description[at0009]/items[at0135]/value").getClass());
        //Element e = (Element)composition.itemAtPath("/content[openEHR-EHR-OBSERVATION.blood_pressure.v1]/data[at0001]/events[at0006]/data[at0003]/items[at0004]");
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>."+e.getValue().getReferenceModelName());
    }
    
    public void printPathNodeMap(){
        System.out.println("### getPathNodeMap" + flattened.getPathNodeMap());
    }

    @Override
    public HashMap<String, String[]> generateMapFromXML(String xml) {

        System.out.println("# generateMapFromXML...");
        //System.out.println("# getPathNodeMap "+ flattened.getPathNodeMap());
        HashMap<String, String[]> map = new HashMap<>();

        try {
            //INCOMPLETOOOOOO!!
            System.out.println("# XML recuperado: \n" + xml);
            COMPOSITION composition = CompositionDocument.Factory.parse(xml).getComposition();
            
            Object rmObj = xmlBinding.bindToRM(composition);
            
            comp = (Composition) rmObj;
            
            System.out.println("# flattened.getPathNodeMap"); //+flattened.getPathNodeMap());
            for (String s : flattened.getPathNodeMap().keySet()) {
                
                //System.out.println("Path Exists: " + comp.pathExists(s));
                //System.out.println("CLASS:" + comp.itemAtPath(s).getClass());

                
                if (comp.itemAtPath(s) != null) {
                    System.out.println("Path : " + s);
                    System.out.println("Class: " + comp.itemAtPath(s).getClass());
                    
                    if (comp.itemAtPath(s) instanceof org.openehr.rm.datatypes.quantity.DvQuantity) {
                        DvQuantity dvq = (DvQuantity) comp.itemAtPath(s);
                        String mag = new Double(dvq.getMagnitude()).toString();
                        String[] arg1 = {mag};
                        //System.out.println("OIE1234");
                        map.put(s, arg1);
                        System.out.println("itemAtPath: " + comp.itemAtPath(s));
                        System.out.println("magnitude: " + mag);
                        
                    } else if (comp.itemAtPath(s) instanceof org.openehr.rm.datatypes.text.DvText) {
                        DvText dct = (DvText) comp.itemAtPath(s);
                        String v = dct.getValue();
                        String[] arg1 = {v};                                            
                        map.put(s, arg1);
                        System.out.println("DvText==" + s);
                        System.out.println("DvText**" + comp.itemAtPath(s));
                        System.out.println("DvText**" + v);
                    } 
//                    else if (comp.itemAtPath(s) instanceof org.openehr.rm.datatypes.encapsulated.DvMultimedia) {
//                        DvMultimedia m = (DvMultimedia) comp.itemAtPath(s);
//                        String v = m.getUri().getValue();
//                        String[] arg1 = {v};
//                        map.put(s, arg1);
//                        System.out.println("DvMultimedia==" + s);
//                        System.out.println("DvMultimedia**" + comp.itemAtPath(s));
//                        System.out.println("DvMultimedia**" + v);
//                    } 
                    else {
                        System.out.println("Path nao tratado: " + s);                        
                    }                    
                }else{
                    System.out.println("Path nulo: "+s);
                }
                System.out.println("");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

    @Override
    public String generateXmlInstance(Map<String, String[]> dataMap) throws Exception {
        System.out.println("## generateXmlInstance:\n");
//        System.out.println("# flattened.getPathNodeMap \\n" +flattened.getPathNodeMap());

        for (Map.Entry<String, String[]> mapEntry : dataMap.entrySet()) {

            //String stringKey = mapEntry.getKey().toString();
            String stringKey = mapEntry.getKey();
            String stringValue = mapEntry.getValue()[0];
            //TODO: Soh funciona com o tipo DvQuantity e DvText: Eh necessario encontrar uma forma de descobrir automaticamente o tipo

//            System.out.println("ITEM : " + comp.itemAtPath(stringKey));
//            System.out.println(">>" + composition.itemAtPath(stringKey));
            if (comp.itemAtPath(stringKey) != null && (!stringValue.isEmpty())) {
                System.out.println("Type: " + comp.itemAtPath(stringKey).getClass());
                System.out.println("KEY : " + stringKey);
                System.out.println("VALUE: " + stringValue);

                if (comp.itemAtPath(stringKey).getClass() == DvQuantity.class) {
                    Double doubleValue = Double.parseDouble(stringValue);
                    DvQuantity dd = (DvQuantity) comp.itemAtPath(stringKey);
                    dd.setMagnitude(doubleValue);
                    comp.set(mapEntry.getKey().toString(), dd);
                    System.out.println("Processed: true");

                } else if (comp.itemAtPath(stringKey).getClass() == DvText.class) {
                    DvText dt = (DvText) comp.itemAtPath(stringKey);
                    dt.setValue(stringValue);
                    comp.set(mapEntry.getKey().toString(), dt);
                    System.out.println("Processed: true");

                } else if (comp.itemAtPath(stringKey).getClass() == DvCodedText.class) {
                    DvCodedText codedText = (DvCodedText) comp.itemAtPath(stringKey);
                    codedText.setValue(stringValue);
                    comp.set(mapEntry.getKey().toString(), codedText);
                    System.out.println("Processed: true");
                } else if (comp.itemAtPath(stringKey).getClass() == DvMultimedia.class) {

////                    DvMultimedia mult = (DvMultimedia) comp.itemAtPath(stringKey);
////                    System.out.println("####getAlternateText#####" + mult.getAlternateText());
////                    System.out.println("####getReferenceModelName#####" + mult.getReferenceModelName());
////                    System.out.println("####getCompressionAlgorithm#####" + mult.getCompressionAlgorithm());
////                    System.out.println("####getData#####" + mult.getData());
////                    System.out.println("####getIntegrityCheck#####" + mult.getIntegrityCheck());
////                    System.out.println("####getIntegrityCheckAlgorithm#####" + mult.getIntegrityCheckAlgorithm());
////                    System.out.println("####getMediaType#####" + mult.getMediaType());
////                    System.out.println("####getSize#####" + mult.getSize());
////                    System.out.println("####getThumbnail#####" + mult.getThumbnail());
////                    System.out.println("####getUri#####" + mult.getUri());
                    
                    //esse funciona mas nao converte para xml
//                    DvMultimedia mult = createSimpleMultimidia(stringValue);
//                    comp.set(mapEntry.getKey().toString(), mult);
//                    System.out.println("DvMultimedia###getData"+mult.getData());                   
//
////                    System.out.println("Processed: ???");
                }
            } else {
                System.out.println("O path definido no mapa chave/valor nao foi encontrado na composicao:");
                System.out.println(stringKey);
            }
            System.out.println();
        }
        
        Object value = xmlBinding.bindToXML(instance);
        XmlObject xmlObj = (XmlObject) value;

//        QName qn = new QName( "composition");
        XmlOptions opts = new XmlOptions();
//        opts.setSaveSyntheticDocumentElement(qn);
//        //opts.setSaveOuter();
        opts.setSavePrettyPrint();
//        //opts.setSavePrettyPrintIndent(4);
        String xml = xmlObj.xmlText(opts);

        //TEMPORARIO (xml-fragment eh gerado porque nao foi possivel o xmlbeans encontrar a tag correta para a definicao do root - talvez a solucao seja a alteracao do schemas openehr)
        String newString = xml.replace("<xml-fragment ", "<composition xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"COMPOSITION\" ");
        String newString2 = newString.replace("</xml-fragment>", "</composition>");
        xml = newString2;

        return xml;

    }

    private DvMultimedia createSimpleMultimidia(String uriReceived) throws Exception {
        CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");
        CodePhrase language = new CodePhrase("ISO_3166-1", "eN");
        String alternateText = "alternative text";
        CodePhrase mediaType = new CodePhrase("IANA_media-types", "text/plain");
        CodePhrase compressionAlgorithm = new CodePhrase("openehr_compression_algorithms", "other");
//        byte[] integrityCheck = new byte[0];
        byte[] integrityCheck = null;
//        CodePhrase integrityCheckAlgorithm = new CodePhrase("openehr_integrity_check_algorithms", "SHA-1");
        CodePhrase integrityCheckAlgorithm = null;
        DvMultimedia thumbnail = null;
        DvURI uri = null;
//        DvURI uri = new DvURI(uriReceived);
//        byte[] data = new byte[0];
        byte[] data = {1,2,3,5};        

        TerminologyService terminologyService = new TestTerminologyService();
        DvMultimedia dm = new DvMultimedia(charset, language, alternateText,
                mediaType, compressionAlgorithm, integrityCheck,
                integrityCheckAlgorithm, thumbnail, uri, data, terminologyService);

        assertNotNull(dm);
        return dm;

    }

//    public String saveDADL(List<String> data) {
//        System.out.println("#####################################CT");
//        System.out.println(data);
//
//        //System.out.println("## saveDADL():\n"+ data);
//        //gravar DADL em disco
//        String result = "0";
//        String dadlFileName = "Composition_MIN.dadl";
//        String xmlFileName = "CompositionTest_MIN.xml";
//
//        File f;
//        String path = "";
//        try {
//            String pathWeb = "C:\\Users\\Iuri\\Documents\\NetBeansProjects\\atoms2.0-CT\\build\\web\\"; //para teste para web
//            String pathSrc = "src/"; //para teste locais            
//            if (new File(pathSrc).exists()) {
//                path = pathSrc;
//            } else if (new File(pathWeb).exists()) {
//                path = pathWeb;
//            }
//        } catch (Exception ex) {
//            System.out.println("Nao eh possivel salvar arquivos nos locais especificados!");
//            ex.printStackTrace();
//            return "1";
//        }
//
//        try {
//            outputStream = new FileOutputStream(new File(path + dadlFileName));
//            collection = new ArrayList<String>(data);
//            IOUtils.writeLines(collection, "\n", outputStream);
//            saveXML(xmlBinding.bindToRM(instance), path + xmlFileName);
//            System.out.println("salvo em: " + path);
//            //printStreamXML(xmlBinding.bindToRM(instance));
//
//        } catch (NullPointerException ex) {
//            result = "1";
//            ex.printStackTrace();
//        } catch (Exception ex) {
//            result = "1";
//            ex.printStackTrace();
//
//        }
//        return result;
//    }
//    public void testDvOrdinalWithLocalCode() throws Exception {
//        archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_seven.v1.adl");
//        instance = generator.create(archetype);
//
//        assertTrue(instance instanceof ItemTree);
//        ItemTree tree = (ItemTree) instance;
//        assertEquals("Medication description", tree.getName().getValue());
//    }
    private void clearFields() throws Exception {
        try {
            outputStream.flush();
            outputStream.close();
            lines.clear();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        collection.clear();

    }

    private void setXmlOptions() throws Exception {
        xmlOptions = new XmlOptions();
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSaveAggressiveNamespaces();
        xmlOptions.setSaveOuter();
        xmlOptions.setSaveInner();
    }

    @Override
    public String update(String ehr, HashMap<String, String[]> multiMediaListUri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
