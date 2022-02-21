package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class RestoreBackupWrapper {

    private IDatabase<Employee> EmployeeDao;
    private IDatabase<Location> LocationDao;
    private IDatabase<AbstractSR> MainSRDao;
    private IDatabase<ComputerSR> ComputerSRDao;

    private CSVReader reader;
    private CSVWriter writer;

    private File backDir;

    // Read paths
    private final String locationFileName = "/TowerLocationsB.csv";
    private final String employeeFileName = "/EmployeeB.csv";
    private final String mainSRFileName = "/MainSRB.csv";
    private final String computerServiceFileName = "/ComputerSRB.csv";

    // Write file names
    private final String locationFileNameW = "TowerLocationsB";
    private final String employeeFileNameW = "EmployeeB";
    private final String mainSRFileNameW = "MainSRB";
    private final String computerServiceFileNameW = "ComputerSRB";

    // First restore paths
    private final String locationFileNameF = "TowerLocationsB.csv";
    private final String employeeFileNameF = "EmployeeB.csv";
    
    RestoreBackupWrapper() {
        LocationDao = new LocationDaoI();
        EmployeeDao = new EmployeeDaoI();
        ComputerSRDao = new ComputerSRDaoI();
        MainSRDao = new MainSRDaoI();
        reader = new CSVReader();
        writer = new CSVWriter();

        String pathString = new File("").getAbsolutePath();
        File f = new File(pathString);

        backDir = new File(f.getAbsolutePath() + "/backup");
    }

    void restoreLocation() throws IOException {

        LocationParserI parser = new LocationParserI();

        File filePath = new File(backDir.getAbsolutePath() + locationFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<Location> locationList = parser.fromStringsToObjects(stringList);

        LocationDao.restoreTable(locationList);
    }

    void backupLocation() throws FileNotFoundException {
        LocationParserI parser = new LocationParserI();

        writer.backupDir(locationFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(LocationDao.getAllValues()));
    }

    void restoreEmployee() throws IOException {

        EmployeeParserI parser = new EmployeeParserI();

        File filePath = new File(backDir.getAbsolutePath() + employeeFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<Employee> employeeList = parser.fromStringsToObjects(stringList);

        EmployeeDao.restoreTable(employeeList);
    }

    void backupEmployee() throws FileNotFoundException {
        EmployeeParserI parser = new EmployeeParserI();

        writer.backupDir(employeeFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(EmployeeDao.getAllValues()));
    }

    void restoreMainSR() throws IOException {

        MainSRParserI parser = new MainSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + mainSRFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<AbstractSR> mainSRList = parser.fromStringsToObjects(stringList);

        MainSRDao.restoreTable(mainSRList);
    }

    void backupMainSR() throws FileNotFoundException {
        MainSRParserI parser = new MainSRParserI();

        writer.backupDir(mainSRFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(MainSRDao.getAllValues()));
    }

    void restoreComputerSR() throws IOException {
        ComputerSRParserI parser = new ComputerSRParserI();

        File filePath = new File(backDir.getAbsolutePath() + computerServiceFileName);
        reader.setFile(filePath);

        List<String> stringList = reader.read();
        List<ComputerSR> computerServiceSRList = parser.fromStringsToObjects(stringList);

        ComputerSRDao.restoreTable(computerServiceSRList);
    }

    void backupComputerSR() throws FileNotFoundException {
        ComputerSRParserI parser = new ComputerSRParserI();

        writer.backupDir(computerServiceFileNameW);
        writer.writeAll(parser.fromObjectsToStrings(ComputerSRDao.getAllValues()));
    }
    
    void restoreAll() throws IOException {
        restoreLocation();
        restoreEmployee();
        restoreMainSR();
        restoreComputerSR();
    }

    void backupAll() throws FileNotFoundException {
        backupLocation();
        backupEmployee();
        backupMainSR();
        backupComputerSR();
    }

    void firstRestore() throws IOException{
        CSVReader reader = new CSVReader();

        LocationParserI parserL = new LocationParserI();
        EmployeeParserI parserE = new EmployeeParserI();


        List<String> locationStringList = reader.firstRestore(locationFileNameF);
        List<String> employeeStringList = reader.firstRestore(employeeFileNameF);

        List<Location> locationList = parserL.fromStringsToObjects(locationStringList);
        List<Employee> employeeList = parserE.fromStringsToObjects(employeeStringList);

        LocationDao.restoreTable(locationList);
        EmployeeDao.restoreTable(employeeList);
    }
}
