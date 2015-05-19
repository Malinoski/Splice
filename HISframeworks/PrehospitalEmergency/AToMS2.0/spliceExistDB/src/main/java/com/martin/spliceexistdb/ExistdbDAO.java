/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package com.martin.spliceexistdb;

import org.exist.xmldb.XmldbURI;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.BinaryResource;

/**
 *
 * @author Iuri
 */
public class ExistdbDAO {

    protected static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    protected static String driver = "org.exist.xmldb.DatabaseImpl";
    protected static String login = "admin";
    protected static String pass = "admin";
    protected Collection col;

    public ExistdbDAO() {
        try {
            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            col = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION, login, pass);

        } catch (Exception ex) {
            System.out.println("### ERRO: Nao foi possivel conectar com o banco de dados eXist! ###");
            ex.printStackTrace();
        }
    }

    public boolean putDocumentResource(String document, String fileName) {
        boolean result = false;

        try {
//            XMLResource res = (XMLResource) col.createResource(fileName, "XMLResource");
            BinaryResource res = (BinaryResource) col.createResource(fileName, "BinaryResource");
            res.setContent(document);
            col.storeResource(res);
            result = true;
            col.close();
        } catch (XMLDBException ex) {
            ex.printStackTrace();
            result = false;
        }

//        // get query-service
//        XPathQueryServiceImpl service
//                = (XPathQueryServiceImpl) col.getService("XPathQueryService", "1.0");
//
//        // set pretty-printing on
//        service.setProperty(OutputKeys.INDENT, "yes");
//        service.setProperty(OutputKeys.ENCODING, "UTF-8");
//
//        // execute queries
//        ResourceSet set = null;
//
//        System.out.println();
//        System.out.println("Query 1");
//        System.out.println("=======");
//        set = service.query("/book/chapter");
//        res = (XMLResource) set.getResource(0);
//        System.out.println(res.getContent());
//
//        System.out.println();
//        System.out.println("Query 2");
//        System.out.println("=======");
//        set = service.query(res, "title");
//        res = (XMLResource) set.getResource(0);
//        System.out.println(res.getContent());
        return result;
    }

    // Para o ECG
    public boolean putBinaryResource(byte[] bin, String fileName){
        boolean result = false;

        try {
            BinaryResource res = (BinaryResource) col.createResource(fileName, "BinaryResource");
            res.setContent(bin);
            col.storeResource(res);
            result = true;
            col.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        }

        return result;
    }

    public String getDocumentResource(String resourceName) {
        byte[] bin = null;
        String resourceContent = null;
        try {
            BinaryResource res = (BinaryResource) col.getResource(resourceName);
            //System.out.println("content: \n"+res.getContent().toString());
            bin = (byte[])res.getContent();
            resourceContent = new String(bin, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resourceContent;
    }

    public byte[] getBinaryResource(String resourceName) {
        byte[] bin = null;
        try {
            BinaryResource res = (BinaryResource) col.getResource(resourceName);
            
//            bin = class2Byte(res.getContent());
            bin = (byte[])res.getContent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bin;
    }
}
