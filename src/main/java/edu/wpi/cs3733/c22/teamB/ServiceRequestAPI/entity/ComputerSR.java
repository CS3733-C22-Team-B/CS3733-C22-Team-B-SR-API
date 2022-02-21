package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity;

import java.time.LocalDate;

public class ComputerSR extends AbstractSR{

    private String helpType;

    public ComputerSR() {
        super(null, "ComputerServiceSR", null, null, null, null, null, null);
        this.helpType = null;
    }

    public ComputerSR(String srID, String status, Location location, Employee requestor, Employee assignedEmployee, LocalDate dateRequested, String notes, String helpType) {
        super(srID, "ComputerServiceSR", status, location, requestor, assignedEmployee, dateRequested, notes);
        this.helpType = helpType;
    }
    public ComputerSR(AbstractSR csr, String helpType) {
        super(csr);
        this.setSrType("ComputerServiceSR");
        this.helpType = helpType;
    }


    public String getHelpType() {
        return helpType;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return "ComputerServiceSR{" +
                "srID= " + getSrID() + '\'' +
                "srType= " + getSrType() + '\'' +
                "status= " + getStatus() + '\'' +
                "location= " + getLocation() + '\'' +
                "requestor= " + getRequestor() + '\'' +
                "assignedEmployee= " + getAssignedEmployee() + '\'' +
                "dateRequested= " + getDateRequested() + '\'' +
                "notes= " + getNotes() + '\'' +
                "helpType='" + helpType + '\'' +
                '}';
    }

    public String toStringFields() {
        return getSrID()
                + ","
                +helpType;
    }

    public static String toStringHeader() {
        return "srID" + "," + "helpType";
    }
}
