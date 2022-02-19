package edu.wpi.cs3733.c22.teamB.entity;

import java.util.List;

public class DatabaseWrapper {

    private IDatabase<Employee> EmployeeDao;
    private IDatabase<Location> LocationDao;

    private ConnectionManager connectionManager;


    public DatabaseWrapper() {
        EmployeeDao = new EmployeeDaoI();
        LocationDao = new LocationDaoI();

        connectionManager = ConnectionManager.getInstance();

    }

    public void initEmbedded() {
        connectionManager.setConnectionStrategy(false);
    }

    public void initClient() {
        connectionManager.setConnectionStrategy(true);
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

    public void createAll() {
        createTableLocation();
        createTableEmployee();
//        createTableMedicalEquipment();
//        createTableSR();
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



}
