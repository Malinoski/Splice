/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package com.martin.spliceexistdb;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author iuri
 */
public class ExistdbDAOTests {

    public static void main(String args[]) throws Exception {
        
//        new ExistdbDAOTests().testEcg();
        new ExistdbDAOTests().testDADL();
        
    }

    public void testEcg() {
        try {
            byte[] buffer = new byte[1024];

            File file = new File("src/main/java/0000000111_iuri_TRECHO1.TEP");
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            while (is.read(buffer) != -1) {
                out.write(buffer);
            }
            byte[] bytes = out.toByteArray();
            System.out.println("bytes length: " + bytes.length);
            boolean result = new ExistdbDAO().putBinaryResource(bytes, "0000000111_iuri_TRECHO1.TEP");
            System.out.println("result: " + result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ///test get ecg (bin)
        byte[] ecgRetrieved = new ExistdbDAO().getBinaryResource("0000000111_iuri_TRECHO1.TEP");
        System.out.println("ecgRetrieved (length)="+ecgRetrieved.length);
    }
    
    public void testDADL() {
        try {
            String dadlContent = ExistDBUtils.readFile("src/main/java/newfile.dadl", StandardCharsets.UTF_8);
            System.out.println("dadlContent:\n"+dadlContent);
            boolean result = new ExistdbDAO().putDocumentResource(dadlContent, "newfile.dadl");
            System.out.println("putDocumentResource:"+result);
            
            String dadlRetrieved = new ExistdbDAO().getDocumentResource("newfile.dadl");
            System.out.println("dadlRetrieved:\n"+dadlRetrieved);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
    }
}
