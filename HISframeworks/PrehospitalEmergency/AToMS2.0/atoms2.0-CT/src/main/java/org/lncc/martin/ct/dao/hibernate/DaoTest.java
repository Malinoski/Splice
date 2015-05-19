/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/

///Classe para testes

package org.lncc.martin.ct.dao.hibernate;

public class DaoTest {

	public static void main(String[] args) {
	
		AnalysisRequestDAO teste = new AnalysisRequestDAO();
		teste.getAllAnalysisRequestPending(1, 1);
		//teste.createAnalysisRequest(22, null, "");
	}

}
