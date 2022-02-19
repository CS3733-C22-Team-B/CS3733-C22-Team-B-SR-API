package edu.wpi.cs3733.c22.teamB.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class DatabaseWrapper {

    private IDatabase<Employee> EmployeeDao;
    private IDatabase<Location> LocationDao;
    private IDatabase<AbstractSR> MainSRDao;
    private IDatabase<ComputerSR> ComputerSRDao;

    private ConnectionManager connectionManager;
    private RestoreBackupWrapper restoreBackupWrapper;



    public DatabaseWrapper() {
        EmployeeDao = new EmployeeDaoI();
        LocationDao = new LocationDaoI();
        MainSRDao = new MainSRDaoI();
        ComputerSRDao = new ComputerSRDaoI();

        connectionManager = ConnectionManager.getInstance();
        restoreBackupWrapper = new RestoreBackupWrapper();

    }

    public void initEmbedded() {
        connectionManager.setConnectionStrategy(false);
    }

    public void initClient() {
        connectionManager.setConnectionStrategy(true);
    }

    public void addSR(AbstractSR abstractSR){
        MainSRDao.addValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ComputerServiceSR":
                ComputerSRDao.addValue((ComputerSR) abstractSR);
                break;

            default:
                System.out.println("Invalid SR Input: " + abstractSR.getSrType());
        }

    }

    public void deleteSR(String srID) {

        AbstractSR abstractSR = getSR(srID);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ComputerServiceSR":
                ComputerSRDao.deleteValue(srID);
                break;
            default:
                System.out.println("Invalid SRID Input: " + abstractSR.getSrID());
        }
        MainSRDao.deleteValue(srID);
    }

    public void updateSR(AbstractSR abstractSR) {
        MainSRDao.updateValue(abstractSR); //TODO do you need this or comment out?ExternalTransportDao.addValue(abstractSR);
        System.out.println(abstractSR.getSrType());
        switch(abstractSR.getSrType()) {
            case "ComputerServiceSR":
                ComputerSRDao.updateValue((ComputerSR) abstractSR);
                break;

            default:
                System.out.println("Invalid SR Input: " + abstractSR.getSrType());
        }
    }

    public AbstractSR getSR(String srID) {

        AbstractSR abstractSR = MainSRDao.getValue(srID);
        if (abstractSR != null) {
            switch(abstractSR.getSrType()) {
                case "ComputerServiceSR":
                    return ComputerSRDao.getValue(srID);
                default:
                    System.out.println("Invalid SR Input: " + abstractSR.getSrType());
            }
        }
        return null;
    }


    public void addEmployee(Employee employee) {
        EmployeeDao.addValue(employee);
    }

    public void deleteEmployee(String employeeID) {
        EmployeeDao.deleteValue(employeeID);
    }

    public void updateEmployee(Employee employee) {
        EmployeeDao.updateValue(employee);
    }

    public Employee getEmployee(String employeeID) {
        return EmployeeDao.getValue(employeeID);
    }

    public List<Employee> getAllEmployee() {
        return EmployeeDao.getAllValues();
    }

//    public List<AbstractSR> getAllSR() {
//        List<AbstractSR> list = MainSRDao.getAllValues();
//
//        for (AbstractSR abstractSR : list) {
//            abstractSR = getSR(abstractSR.getSrID());
//        }
//
//        System.out.println(list);
//        return list;
//    }

    public void addLocation(Location location) {
        LocationDao.addValue(location);
    }

    public void createTableLocation() {
        LocationDao.createTable();
}
    public void dropTableLocation() {
        LocationDao.dropTable();
    }
    public void deleteLocation(String locationID) {
        LocationDao.deleteValue(locationID);
    }
    public void updateLocation(Location location) {
        LocationDao.updateValue(location);
    }
    public Location getLocation(String locationID) {
        return LocationDao.getValue(locationID);
    }
    public List<Location> getAllLocation() {
        return LocationDao.getAllValues();
    }

    public void createTableEmployee() {
        EmployeeDao.createTable();
    }

    public void createTableSR() {
        MainSRDao.createTable();
        ComputerSRDao.createTable();
    }

    public void createAll() {
        createTableLocation();
        createTableEmployee();
        createTableSR();
    }

    public void dropTableSR() {

        ComputerSRDao.dropTable();
        MainSRDao.dropTable();
    }

    public void dropTableEmployee() {
        EmployeeDao.dropTable();
    }

    public void dropAll() {

//        dropTableSR();
//        dropTableMedicalEquipment();
        dropTableEmployee();
        dropTableLocation();
    }

    public List<AbstractSR> getAllSR() {
        List<AbstractSR> list = MainSRDao.getAllValues();

        for (AbstractSR abstractSR : list) {
            abstractSR = getSR(abstractSR.getSrID());
        }

        System.out.println(list);
        return list;
    }


    void restoreTableLocation() throws IOException {
        restoreBackupWrapper.restoreLocation();
    }

    void restoreTableEmployee() throws IOException {
        restoreBackupWrapper.restoreEmployee();
    }

    void restoreTableSR() throws IOException {
        restoreBackupWrapper.restoreMainSR();
        restoreBackupWrapper.restoreComputerSR();
    }

    public void restoreAll() throws IOException {
        System.out.println("Restore" + ConnectionManager.getInstance().getConnection());
        dropAll();
        createAll();
        restoreBackupWrapper.restoreAll();
    }

    void backupTableLocation() throws IOException {
        restoreBackupWrapper.backupLocation();
    }

    void backupTableEmployee() throws IOException {
        restoreBackupWrapper.backupEmployee();
    }

    void backupTableSR() throws FileNotFoundException {
        restoreBackupWrapper.backupMainSR();
        restoreBackupWrapper.backupComputerSR();
    }

    public void backupAll() throws IOException{
        restoreBackupWrapper.backupAll();
        System.out.println(ConnectionManager.getInstance().getConnection());
    }

    public void firstRestore() throws IOException {
        restoreBackupWrapper.firstRestore();
        createAll();
    }

    // Clean up for Iteration 3
    public void isFirstRestore() throws IOException {

        LocationDaoI test = new LocationDaoI();
        if (test.isFirstRestore()){
            firstRestore();
        } else{
            System.out.println("Not First Restore!");
        }
    }
    public Connection getConnection() {
        return ConnectionManager.getInstance().getConnection();
    }


}
