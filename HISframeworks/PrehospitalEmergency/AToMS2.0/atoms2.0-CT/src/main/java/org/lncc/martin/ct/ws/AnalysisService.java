/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.ct.ws;

import java.util.HashMap;
import org.lncc.martin.ct.dao.AnalysisDAO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam; 

/**
 *
 * @author Iuri
 */
@WebService(serviceName = "AnalysisService")
public class AnalysisService {

    /**
     * Operação de Web service
     * @param analysisRequest
     * @param ecgs
     */
    @WebMethod(operationName = "sendAnalysisRequest")
    public long sendAnalysisRequest(
            @WebParam(name = "analysisRequest") AnalysisModel analysisRequest, @WebParam(name = "multimediaMap") List<MultiMedia> multiMediaList){
        return new AnalysisDAO().saveAnalysisRequest(analysisRequest, multiMediaList);
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "sendAnalysisResponse")
    public boolean sendAnalysisResponse(
            @WebParam(name = "idAnalysis") long idAnalysis,            
            @WebParam(name = "xml") String xml) {        
        return new AnalysisDAO().createAnalysisResponse(idAnalysis, xml);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getAnalysisRequest")
    public AnalysisModel getAnalysisRequest(
            @WebParam(name = "analysisId") long analysisId,
            @WebParam(name = "specialistId") long specialistId) {
        return new AnalysisDAO().getAnalysisRequest(analysisId,specialistId);        
    }
    
    @WebMethod(operationName = "getXMLAnalysisResponse")
    public String getXMLAnalysisResponse(@WebParam(name = "idAnalysis") long idAnalysis) {        
        return new AnalysisDAO().getXMLAnalysisResponse(idAnalysis);        
    }
    
    @WebMethod(operationName = "getAllAnalysisRequestPending")
    public List<AnalysisModel> getAllAnalysisRequestPending(
            @WebParam(name = "idArea") long idArea,
            @WebParam(name = "idUser") long idUser) {
        return new AnalysisDAO().getAllAnalysisRequestPending(idArea, idUser);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "searchAnalysisResponse")
    public String searchAnalysisResponse(@WebParam(name = "idAnalysis") long idAnalysis) {        
        return new AnalysisDAO().getXMLFileNameFromAnalysisResponse(idAnalysis);
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getAnalysisResponse")
    public AnalysisModel getAnalysisResponse(@WebParam(name = "analysisId") long analysisId) {
        return new AnalysisDAO().getAnalysisResponse(analysisId);        
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "finishAnalysis")
    public Boolean finishAnalysis(@WebParam(name = "analysisId") long analysisId) {
        return new AnalysisDAO().finishAnalysis(analysisId);        
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getEcgList")
    public List<byte[]> getEcgList(@WebParam(name = "analysisId") long analysisId) {
        return new AnalysisDAO().getEcgList(analysisId);        
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "getMultimedia")    
    public byte[] getMultimedia(String fileName) {
        return new AnalysisDAO().getMultimedia(fileName);
    }
    
//    /**
//     * Operação de Web service
//     */
//    @WebMethod(operationName = "sendEcgList")
//    public String sendEcgList(
//            @WebParam(name = "analysisId") long analysisId, 
//            @WebParam(name = "ecgList") List<byte[]> ecgs) {
//        //TODO
//        return null;
//    }
//    
    
}
