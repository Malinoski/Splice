/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

/// Classe de mapeamento Obs x Banco Relacional utilizando Hibernate com anotações. 
/// É necessário atualizar o arquivo src/main/resources/hibernate.cfg.xml com as devidas informações do banco 
 
package org.lncc.martin.ct.dao.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table (name="analysis_request", schema="public")
public class AnalysisRequest {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;

	@Column(name="status", length = 1)
	private char status;
	
	@Column(name="data_cadastro")
	private String dataCadastro;
	
	@Column(name="id_especialista")
	private Long idEspecialista;
	
	@Column(name="id_emergencista")
	private Long idEmergencista;
	
	@Column(name="id_EHR_Obs")
	private String idEhrObs;
	
	@Column(name="id_EHR_Inst")
	private String idEhrInst;
	
	
	public AnalysisRequest() {
	}

	public AnalysisRequest(int id) {
		this.id = id;
	}

	public AnalysisRequest(int id, char status, String dataCadastro,
			long idEspecialista, long idEmergencista, String idEhrObs,
			String idEhrInst) {
		this.id = id;
		this.status = status;
		this.dataCadastro = dataCadastro;
		this.idEspecialista = idEspecialista;
		this.idEmergencista = idEmergencista;
		this.idEhrObs = idEhrObs;
		this.idEhrInst = idEhrInst;
	}

	
	@Column(name="id", nullable = false)
	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="status", length = 1)
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Column(name="data_cadastro")
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Column(name="id_especialista")
	public long getIdEspecialista() {
		return idEspecialista;
	}

	public void setIdEspecialista(long idEspecialista) {
		this.idEspecialista = idEspecialista;
	}

	@Column(name="id_emergencista")
	public long getIdEmergencista() {
		return idEmergencista;
	}

	public void setIdEmergencista(long idEmergencista) {
		this.idEmergencista = idEmergencista;
	}

	@Column(name="id_EHR_Obs")
	public String getIdEhrObs() {
		return idEhrObs;
	}

	public void setIdEhrObs(String idEhrObs) {
		this.idEhrObs = idEhrObs;
	}

	@Column(name="id_EHR_Inst")
	public String getIdEhrInst() {
		return idEhrInst;
	}

	public void setIdEhrInst(String idEhrInst) {
		this.idEhrInst = idEhrInst;
	}
}
