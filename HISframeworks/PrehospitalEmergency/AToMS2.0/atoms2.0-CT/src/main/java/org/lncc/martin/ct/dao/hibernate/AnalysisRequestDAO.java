/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

///Sua classe adaptada para usar a implementação baseada em Hibernate
 
package org.lncc.martin.ct.dao.hibernate;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AnalysisRequestDAO {
	
	public synchronized List<AnalysisRequest> getAllAnalysisRequestPending(long area, long idEsp){
		return new RelDbManager().getAllPendingAnalysisRequests(area);
	}
	
	public synchronized long createAnalysisRequest(long idEmergencista, List<byte[]> ecgList, String xmlEhr){
		
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
        
        String hour = hour_of_dayFormated + "-" + minuteFormated + "-" + seconds ;
        String dataEhora = date + "--" + hour;
        
        RelDbManager relDb = new RelDbManager();
        
        long lastIdAnalysisReq = relDb.createAnalysisRequest(dataEhora, idEmergencista);
        
        String fileName = saveClinicalData(xmlEhr,dataEhora, lastIdAnalysisReq);
        relDb.updateAnalysisRequestWithIdEhrObs(fileName, lastIdAnalysisReq);
        
        return 1;		
	}
	
	public String saveClinicalData(String xml, String dataEhora, long idAnalysisRequest) {
        String fileName = idAnalysisRequest + "---" + dataEhora+".xml";
        //System.out.println("***FILENAME: "+fileName);
        /*
        try {
            //String fileName = "analysisRequest001.xml";
            //TODO a string "analysisRequest" abaixo deve ser gerada pela CT dinamicaemnte!!!!!
            if (new ExistdbDAO().put(xml, fileName)) {
                return fileName;
            }
        } catch (Exception ex) {
            //Logger.getLogger(AnalysisService.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }*/
        return fileName;
    }

}
