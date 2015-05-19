/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

/// Implementacao dos metodos utilizados para manipular o banco relacional.

package org.lncc.martin.ct.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RelDbManager {
	
	public List<AnalysisRequest> getAllPendingAnalysisRequests(long area){

		List<AnalysisRequest> pendingRequests = new ArrayList<AnalysisRequest>();
		
		HibernateUtil hibUtil = new HibernateUtil();
		Session session = hibUtil.getConnection();
		
		try{
			Query query = (Query) session.createQuery("from AnalysisRequest where status = 'N'");
			pendingRequests = (ArrayList<AnalysisRequest>) query.list(); 
			
			System.out.println("--------------------Numero de requisições: " + pendingRequests.size());
			
			session.close();
			hibUtil.closeConnection();
			
			return pendingRequests;
			
		} catch(Exception e){
			System.out.println("Problemas com a busca por requisicoes: " + e);
			
			session.close();
			hibUtil.closeConnection();
			
			return null;
		}
	}
	
	public long createAnalysisRequest(String date, long idEmergencista){
		
		AnalysisRequest ar = new AnalysisRequest();
		
		HibernateUtil hibUtil = new HibernateUtil();
		Session session = hibUtil.getConnection();
		Transaction trans = session.beginTransaction();
		
		try{
			ar.setDataCadastro(date);
			ar.setIdEmergencista(idEmergencista);
			ar.setIdEspecialista(-1);
			ar.setStatus("N".charAt(0));
			session.saveOrUpdate(ar);
			trans.commit();
			session.close();
			hibUtil.closeConnection();
			
			System.out.println("-------------------------ID da requisicao criada: " + ar.getId());
			
			return ar.getId();
			
		} catch(Exception e){
			System.out.println("Problemas na criação da requisição: " + e);
			
			trans.rollback();
			session.close();
			hibUtil.closeConnection();
			
			return -1;
		}
		
		
	}
	
	public boolean updateAnalysisRequestWithIdEhrObs(String idEhrObs, long idAnalysisRequest){
		
		AnalysisRequest ar = new AnalysisRequest();
		
		HibernateUtil hibUtil = new HibernateUtil();
		Session session = hibUtil.getConnection();
		Transaction trans = session.beginTransaction();
		
		try{
			int id = (int) idAnalysisRequest;
			
			ar = (AnalysisRequest) session.load(AnalysisRequest.class, id);
			ar.setIdEhrObs(idEhrObs);
			
			session.update(ar);
			trans.commit();
			session.close();
			hibUtil.closeConnection();
			
			return true;
			
		} catch (Exception e){
			System.out.println("Problemas com a inserção do idEhrObs: " + e);
			
			trans.rollback();
			session.close();
			hibUtil.closeConnection();
			
			return false;
		}
		
		
	}
	
	public boolean updateAnalysisRequestWithIdEhrInst(String idEhrInst, long idAnalysisRequest){
		
		AnalysisRequest ar = new AnalysisRequest();
		
		HibernateUtil hibUtil = new HibernateUtil();
		Session session = hibUtil.getConnection();
		Transaction trans = session.beginTransaction();
		
		try{
			int id = (int) idAnalysisRequest;
			
			ar = (AnalysisRequest) session.load(AnalysisRequest.class, id);
			ar.setIdEhrInst(idEhrInst);
			
			session.update(ar);
			trans.commit();
			session.close();
			hibUtil.closeConnection();
			
			return true;
			
		} catch(Exception e){
			System.out.println("Problemas com a inserção do idEhrInst: " + e);
			
			trans.rollback();
			session.close();
			hibUtil.closeConnection();
			
			return false;
		}
		
		
	}
	
	public boolean updateAnalysisRequestWithStatus(char status, long idAnalysisRequest){
		
		AnalysisRequest ar = new AnalysisRequest();
		
		HibernateUtil hibUtil = new HibernateUtil();
		Session session = hibUtil.getConnection();
		Transaction trans = session.beginTransaction();
		
		try{
			int id = (int) idAnalysisRequest;
			
			ar = (AnalysisRequest) session.load(AnalysisRequest.class, id);
			ar.setStatus(status);
			
			session.update(ar);
			trans.commit();
			session.close();
			hibUtil.closeConnection();
			
			return true;
			
		} catch(Exception e){
			System.out.println("Problemas com a inserção do idAnalysisRequest: " + e);
			
			trans.rollback();
			session.close();
			hibUtil.closeConnection();
			
			return false;
		}
	}
}
