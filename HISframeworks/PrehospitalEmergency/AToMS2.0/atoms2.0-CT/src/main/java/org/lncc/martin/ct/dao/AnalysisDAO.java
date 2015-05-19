/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.ct.dao;

import CONTROL.ArchetypeController;
import com.martin.spliceexistdb.ExistdbDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.lncc.martin.ct.ws.AnalysisModel;
import org.lncc.martin.ct.ws.MultiMedia;

public class AnalysisDAO {

    private ArchetypeController ac;

    public AnalysisDAO() {
        ac = new ArchetypeController(
                "/home/iuri/Dropbox/SPLICE/archetypes_2014_03_26-17_23_42",
                "/home/iuri/Dropbox/SPLICE/templates/");
    }

    public synchronized List<AnalysisModel> getAllAnalysisRequestPending(Long area, Long idEsp) {
        List<AnalysisModel> arList = new ArrayList<AnalysisModel>();
        //FAKE
//        for (int i = 1; i < 3; i++) {
//            AnalysisModel a = new AnalysisModel();
//            a.setId(i);
//            a.setIdEhrInst("INST URI " + i);
//            a.setIdEhrObs("20-3-2014--00-21-12.xml");
//            a.setIdEmergencista(1);
//            a.setIdEspecialista(2);
//            a.setDataCadastro("alguma hora " + i);
//            arList.add(a);
//        }
        arList = new JDBCExample().getAllAnalysisRequestPendingJDBC(area);
        return arList;
    }

    public String getXMLAnalysisResponse(long idAnalysis) {
        String xmlFileName = new JDBCExample().getXmlFileNameFromAnalysisResponse(idAnalysis);
        return new ExistdbDAO().getDocumentResource(xmlFileName);
    }

    public synchronized long saveAnalysisRequest(AnalysisModel analysisRequest, List<MultiMedia> multiMediaList) {
        String dataEhora = createDataEHora();
        analysisRequest.setDataCadastro(dataEhora);
        analysisRequest.setStatus("P");

        //Salvando informações arquiteturais (banco relacional)
        long lastIdAnalysisReq = -1;
        lastIdAnalysisReq = new JDBCExample().createAnalysisRequestJDBC(analysisRequest);
        if (lastIdAnalysisReq == -1) {
            System.out.println("## Ocorreu um erro ao registrar a analise no banco relacional!");
            return -1;
        }

        //Salvando multimedia no banco de dados relacional
        int i = 0;
        HashMap<String, String[]> multiMediaListUri = new HashMap<String, String[]>();// Hash map para guardar todos os paths multimidia e o nome do arquivo (URI)
        for (MultiMedia multimedia : multiMediaList) {
            String ecgFileName = lastIdAnalysisReq + "---" + i + "---" + dataEhora + ".TEP";
            i++;
            boolean result = saveECG(multimedia.getBytes(), ecgFileName);
            if (!result) {
                System.out.println("## Ocorreu um erro ao salvar o ecg no banco xml!");
                return -1;
            }
            String[] mult = {ecgFileName}; //Aqui, um array é para outros atributos do DvMultimedia, mas será utilizado soemnte a URI (nome do arquivo)
            multiMediaListUri.put(multimedia.getPath(), mult);
        }

        //TODO!!
        //ATUALIZAR NO DADL A URI DO ARQUIVO AQUI!!!!!!!
        String ehr = analysisRequest.getObservation().getFileContent();
//        String finalEhr = update(ehr, multiMediaListUri);
        String finalEhr = ac.update(ehr, multiMediaListUri);

        //Salvando openehr no banco de dados xml
        String fileName = lastIdAnalysisReq + "---" + dataEhora + ".xml";
        boolean updateResult = new JDBCExample().updateAnalysisRequestJDBC(fileName, lastIdAnalysisReq);
        boolean saveClinicalDataResult = saveClinicalData(finalEhr, fileName);
        if (!updateResult || !saveClinicalDataResult) {
            System.out.println("## Ocorreu um erro no update da analise no banco relacional!");
            return -1;
        }

        return lastIdAnalysisReq;
    }

    public synchronized boolean createAnalysisResponse(long idAnalysis, String xmlEhr) {
        System.out.println("# createAnalysisResponse");
        boolean result = false;
        try {
            String dataEhora = createDataEHora();
            String fileName = idAnalysis + "---" + dataEhora + ".xml";
            if (saveClinicalData(xmlEhr, fileName)
                    && new JDBCExample().updateAnalysisResponseJDBC(idAnalysis, fileName)) {
                result = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    //public String saveFile(Object obj, String dataEhora, long idAnalysisRequest) {
    public boolean saveClinicalData(String obj, String fileName) {
        boolean result = false;
        System.out.println("# saving XML (clinical dada) - file name: " + fileName);
        try {
            //String fileName = "analysisRequest001.xml";
            //TODO a string "analysisRequest" abaixo deve ser gerada pela CT dinamicaemnte!!!!!
            if (new ExistdbDAO().putDocumentResource(obj, fileName)) {
                result = true;
            }
        } catch (Exception ex) {
            //Logger.getLogger(AnalysisService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    public boolean saveECG(byte[] obj, String fileName) {
        boolean result = false;
        System.out.println("# saving ecg: file name - " + fileName);
        try {
            //String fileName = "analysisRequest001.xml";
            //TODO a string "analysisRequest" abaixo deve ser gerada pela CT dinamicaemnte!!!!!
            if (new ExistdbDAO().putBinaryResource(obj, fileName)) {
                result = true;
            }
        } catch (Exception ex) {
            //Logger.getLogger(AnalysisService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    private String createDataEHora() {
        Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
        String date = c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.YEAR);

        //Pegando e formatando
        String hour_of_day = "";
        hour_of_day = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String hour_of_dayFormated = "";
        if (Integer.parseInt(hour_of_day) < 10) {
            hour_of_dayFormated = "0" + hour_of_day;
        } else {
            hour_of_dayFormated = hour_of_day;
        }

        String minute = "";
        minute = String.valueOf(c.get(Calendar.MINUTE));
        String minuteFormated = "";
        if (Integer.parseInt(minute) < 10) {
            minuteFormated = "0" + minute;
        } else {
            minuteFormated = minute;
        }

        String seconds = Integer.toString(c.get(Calendar.SECOND));

        String hour = hour_of_dayFormated + "-" + minuteFormated + "-" + seconds;
        return date + "--" + hour;
    }

    public String getXMLFileNameFromAnalysisResponse(long idAnalysis) {
        return new JDBCExample().getXmlFileNameFromAnalysisResponse(idAnalysis);
    }

    public AnalysisModel getAnalysisRequest(long analysisId, long specialistId) {
        //Relational DataBase
        AnalysisModel a = new JDBCExample().getAnalysis(analysisId);
        new JDBCExample().setAnalysisInProgress(analysisId, specialistId);

        //XML Database
        String xml = new ExistdbDAO().getDocumentResource(a.getObservation().getFileName());
        a.getObservation().setFileContent(xml);

        return a;
    }

    public AnalysisModel getAnalysisResponse(long analysisId) {
        //Relational DataBase
        AnalysisModel a = new JDBCExample().getAnalysis(analysisId);

        //XML Database
        String xml = new ExistdbDAO().getDocumentResource(a.getInstruction().getFileName());
        a.getInstruction().setFileContent(xml);

        return a;
    }

    public Boolean finishAnalysis(long analysisId) {
        return new JDBCExample().finishAnalysis(analysisId);
    }

    public List<byte[]> getEcgList(long analysisId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] getMultimedia(String fileName) {
        try {
            byte[] bytes = new ExistdbDAO().getBinaryResource(fileName);
            System.out.println("# Collecting multimedia: file Name=" + fileName + "; bytes length=" + bytes.length);
            return bytes;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    private String update(String ehr, HashMap<String, String[]> mult) {
//
//        //TODO - Aqui entra o metodo do vinicius para dadl (nao xml)
//        //////////////////////////////////
//        ////////////////////////TEMPORARIO - super gambiarra contra o tempo!
//        //////////////////////////////////        
//        for (Map.Entry<String, String[]> mapEntry : mult.entrySet()) {
//            ehr = ehr.replace("www.iana.org", mapEntry.getValue()[0]);
//        }
//        return ehr;
//        
//        
//    }

}
