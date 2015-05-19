/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.ct.ws;

import java.util.ArrayList;
import java.util.List;

public class AnalysisModel{

    private long id;
    private String status;
    private String dataCadastro;

    private long idEspecialista;
    private long idEmergencista;

    private ClinicalFileModel observation;
    private ClinicalFileModel instruction;

//    public AnalysisModel() {
//        ecgs = new ArrayList<byte[]>();
//    }
    
    
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
        
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dataCadastro
     */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the idEspecialista
     */
    public long getIdEspecialista() {
        return idEspecialista;
    }

    /**
     * @param idEspecialista the idEspecialista to set
     */
    public void setIdEspecialista(long idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    /**
     * @return the idEmergencista
     */
    public long getIdEmergencista() {
        return idEmergencista;
    }

    /**
     * @param idEmergencista the idEmergencista to set
     */
    public void setIdEmergencista(long idEmergencista) {
        this.idEmergencista = idEmergencista;
    }

    /**
     * @return the observation
     */
    public ClinicalFileModel getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(ClinicalFileModel observation) {
        this.observation = observation;
    }

    /**
     * @return the instruction
     */
    public ClinicalFileModel getInstruction() {
        return instruction;
    }

    /**
     * @param instruction the instruction to set
     */
    public void setInstruction(ClinicalFileModel instruction) {
        this.instruction = instruction;
    }    
}
