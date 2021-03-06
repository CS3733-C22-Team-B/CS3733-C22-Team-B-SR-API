package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComputerSRDaoI implements IDatabase<ComputerSR>{

    public ComputerSRDaoI() {
    }


    @Override
    public void addValue(ComputerSR object) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO COMPUTERSERVICESR (srID, helpType) VALUES(?, ?)");
            pstmt.setString(1, object.getSrID());
            pstmt.setString(2, object.getHelpType());


            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            System.out.println("Insert Into COMPUTERSERVICESR Table: Failed!");
            e.printStackTrace();
        }
    }


    @Override
    public void deleteValue(String objectID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM COMPUTERSERVICESR WHERE srID = ?");
            pstmt.setString(1, objectID);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Delete From COMPUTERSERVICESR Table Using SR ID: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateValue(ComputerSR object) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement(
                            "UPDATE COMPUTERSERVICESR SET HELPTYPE = ? WHERE srID = ?");

            pstmt.setString(1, object.getHelpType());
            pstmt.setString(2, object.getSrID());


            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Update COMPUTERSERVICESR Node: Failed!");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public ComputerSR getValue(String objectID) {
        Connection conn = ConnectionManager.getInstance().getConnection();

        ComputerSR computerServiceSR = new ComputerSR();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM COMPUTERSERVICESR WHERE srID = ?");
            pstmt.setString(1, objectID);
            ResultSet rset = pstmt.executeQuery();

            rset.next();


            String helpType = rset.getString("helpType");

            MainSRDaoI mainSRDaoI = new MainSRDaoI();
            AbstractSR mainSR = mainSRDaoI.getValue(objectID);

            String status = mainSR.getStatus();
            Location location = mainSR.getLocation();
            Employee requestor = mainSR.getRequestor();
            Employee assignedEmployee = mainSR.getAssignedEmployee();
            LocalDate dateRequested = mainSR.getDateRequested();
            String notes = mainSR.getNotes();


            computerServiceSR = new ComputerSR(objectID, status, location, requestor, assignedEmployee, dateRequested, notes, helpType);

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Get COMPUTERSERVICESR Node Failed");
            e.printStackTrace();
        }
        return computerServiceSR;
    }

    @Override
    public List<ComputerSR> getAllValues() {
        Connection conn = ConnectionManager.getInstance().getConnection();

        List<ComputerSR> computerServiceSRList = new ArrayList<>();

        try{
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT SRID FROM COMPUTERSERVICESR ");
            ResultSet rset = pstmt.executeQuery();


            while(rset.next()){
                computerServiceSRList.add(getValue(rset.getString("SRID")));
            }
        } catch (SQLException e) {
            System.out.println("Get COMPUTERSERVICESR Node Failed");
            e.printStackTrace();
        }
        return computerServiceSRList;
    }

    @Override
    public void createTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rset = dbmd.getTables(null, null, "COMPUTERSERVICESR", null);

            if (rset.next() && rset.getString(3).equals("COMPUTERSERVICESR")){
                // table exists
            } else {
                // Create table
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE COMPUTERSERVICESR ( "
                        + "srID VARCHAR(50) , "
                        + "helpType VARCHAR(50), "
                        + "PRIMARY KEY (srID),"
                        + "CONSTRAINT FK_COMPUTERSERVICESR_MainSR FOREIGN KEY (srID) REFERENCES MainSR (srID) )"); //ON DELETE SET NULL
            }
        } catch (SQLException e) {
            System.out.println("Create COMPUTERSERVICESR Table: Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        Connection conn = ConnectionManager.getInstance().getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE COMPUTERSERVICESR");
        } catch (SQLException e) {
            System.out.println("Drop COMPUTERSERVICESR Table: Failed!");
        }
    }

    @Override
    public void restoreTable(List<ComputerSR> list) {

        createTable();

        for (ComputerSR computerServiceSR : list) {
            addValue(computerServiceSR);
        }
    }
}
