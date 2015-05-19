/*******************************************************************************
 * Copyright © 2011-2015, MARTIN Development Group / LNCC. All Rights Reserved.
 * Read file LICENSE.txt for conditions on the use of this software.
 *******************************************************************************/
package org.lncc.martin.ct.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lncc.martin.ct.ws.AnalysisModel;
import org.lncc.martin.ct.ws.ClinicalFileModel;

public class JDBCExample {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/atoms2.0";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "olmatae";

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public JDBCExample() {
        System.out.println("# JDBCExample");
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.postgresql.Driver");
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected database successfully...");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        // new JDBCExample().createAnalysisRequestJDBC("aaaaa", 3);
        //new JDBCExample().updateAnalysisRequestJDBC("bbbbb", 8);
        //new JDBCExample().getAllAnalysisRequestPendingJDBC(1);
        //new JDBCExample().updateAnalysisResponseJDBC(1, "ArquivoInstrucao", 3);
        new JDBCExample().getXmlFileNameFromAnalysisResponse(43);
    }

    public long createAnalysisRequestJDBC(AnalysisModel analysis) {
        int autoIncKeyFromApi = -1;
        try {
            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO analysis_request (status, data_cadastro, id_emergencista) "
                    + "VALUES ('" + analysis.getStatus() + "', '" + analysis.getDataCadastro() + "', " + analysis.getIdEmergencista() + ")";
            System.out.println("SQL: " + sql);
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {
                // throw an exception from here
            }

            System.out.println("Inserted records into the table... auto increment is: " + autoIncKeyFromApi);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();

            }//end finally try

        }//end try
        //System.out.println("Goodbye!");
        return autoIncKeyFromApi;
    }

    public boolean updateAnalysisRequestJDBC(String idEhrObs, long idAnalysisRequest) {
        boolean result = false;

        try {

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE analysis_request "
                    + "SET id_ehr_obs = '" + idEhrObs + "' WHERE id = " + idAnalysisRequest;
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return result;
    }

    public boolean updateAnalysisResponseJDBC(long idAnalysisRequest, String idEhrInst) {
        boolean result = false;

        try {

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE analysis_request "
                    + "SET id_ehr_inst = '" + idEhrInst + "', status = 'A' WHERE id = " + idAnalysisRequest;
            // A = Andamento
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return result;
    }

    public List<AnalysisModel> getAllAnalysisRequestPendingJDBC(long area) {

        //TODO long area caso tenha alguma área no futuro do desenvolvimento
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, status, data_cadastro, id_emergencista, id_ehr_obs FROM analysis_request where status='P' order by id";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set

            List<AnalysisModel> arList = new ArrayList<AnalysisModel>();
            while (rs.next()) {

                //Retrieve by column name
                int id = rs.getInt("id");
                String status = rs.getString("status");
                String dataCadastro = rs.getString("data_cadastro");
                int idEmergencista = rs.getInt("id_emergencista");
                String idEhrObs = rs.getString("id_ehr_obs");

                AnalysisModel a = new AnalysisModel();
                a.setId(id);
                a.setStatus(status);
                a.setDataCadastro(dataCadastro);
                a.setIdEmergencista(area);
                ClinicalFileModel clinical = new ClinicalFileModel();
                clinical.setFileName(idEhrObs);
                a.setObservation(clinical);
                arList.add(a);

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", status: " + status);
                System.out.print(", data_cadastro: " + dataCadastro);
                System.out.print(", id_ehr_obs: " + idEhrObs);
                System.out.println(", idEmergencista: " + idEmergencista);
            }
            rs.close();
            return arList;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    public String getXmlFileNameFromAnalysisResponse(long idAnalysis) {
        System.out.println("getXMLFileNameFromAnalysisResponse(idAnalysis=" + idAnalysis + ")");
        //TODO long area caso tenha alguma área no futuro do desenvolvimento
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id_ehr_inst FROM analysis_request where id=" + idAnalysis + " and status='A'";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            String idEhrInst = null;
            while (rs.next()) {
                //Retrieve by column name
                idEhrInst = rs.getString("id_ehr_inst");
                //Display values
                System.out.print("id_ehr_inst: " + idEhrInst);
            }
            rs.close();
            return idEhrInst;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return null;
    }

    AnalysisModel getAnalysis(long analysisId) {
        AnalysisModel a = null;

        //TODO long area caso tenha alguma área no futuro do desenvolvimento
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, status, data_cadastro, id_emergencista, id_especialista, id_ehr_obs, id_ehr_inst FROM analysis_request where id='" + analysisId + "'";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set

            List<AnalysisModel> arList = new ArrayList<AnalysisModel>();
            while (rs.next()) {

                a = new AnalysisModel();
                a.setId(rs.getInt("id"));
                a.setIdEmergencista(rs.getInt("id_emergencista"));
                a.setIdEspecialista(rs.getInt("id_especialista"));
                a.setStatus(rs.getString("status"));
                a.setDataCadastro(rs.getString("data_cadastro"));
                ClinicalFileModel obsModel = new ClinicalFileModel();
                obsModel.setFileName(rs.getString("id_ehr_obs"));
                a.setObservation(obsModel);
                ClinicalFileModel instModel = new ClinicalFileModel();
                instModel.setFileName(rs.getString("id_ehr_inst"));
                a.setInstruction(instModel);
                arList.add(a);

                System.out.println("ID              : " + a.getId());
                System.out.println("status          : " + a.getStatus());
                System.out.println("data_cadastro   : " + a.getDataCadastro());
                System.out.println("id_ehr_obs      : " + a.getObservation().getFileName());
                System.out.println("id_ehr_inst     : " + a.getInstruction().getFileName());
                System.out.println("idEmergencista  : " + a.getIdEmergencista());
                System.out.println("idEmergencista  : " + a.getIdEspecialista());
            }
            rs.close();
            return arList.get(0);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

        return a;
    }

    public boolean setAnalysisInProgress(long idAnalysis, long idSpecialist) {
        boolean result = false;

        try {

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE analysis_request "
                    + "SET status = 'A', id_especialista = " + idSpecialist + " WHERE id = " + idAnalysis;
            /// A = Andamento
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {
            System.out.println("oie sql");
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try        
        System.out.println("Goodbye!");
        return result;
    }    

    Boolean finishAnalysis(long analysisId) {
        boolean result = false;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE analysis_request SET status = 'F' WHERE id = " + analysisId + ""; // F = Finalizado
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return result;
    }
}
