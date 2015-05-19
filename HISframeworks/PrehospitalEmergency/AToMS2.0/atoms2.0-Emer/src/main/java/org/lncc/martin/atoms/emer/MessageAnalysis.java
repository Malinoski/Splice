/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

package org.lncc.martin.atoms.emer;

import java.util.HashMap;

public class MessageAnalysis {

    private Long id;
    private String other;
    private String status;
    private String dataCadastro;
    private String horaCadastro;
    private String loginParamedico;
    private HashMap<String,String[]> EHRmap;
    private long type; // 1 = Pendente; 2 = Andamento; 3 = Acompanhamento
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(String horaCadastro) {
        this.horaCadastro = horaCadastro;
    }

    public String getLoginParamedico() {
        return loginParamedico;
    }

    public void setLoginParamedico(String loginParamedico) {
        this.loginParamedico = loginParamedico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    /**
     * @return the other
     */
    public String getOther() {
        return other;
    }

    /**
     * @param other the other to set
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * @return the EHRmap
     */
    public HashMap<String,String[]> getEHRmap() {
        return EHRmap;
    }

    /**
     * @param EHRmap the EHRmap to set
     */
    public void setEHRmap(HashMap<String,String[]> EHRmap) {
        this.EHRmap = EHRmap;
    }
}
