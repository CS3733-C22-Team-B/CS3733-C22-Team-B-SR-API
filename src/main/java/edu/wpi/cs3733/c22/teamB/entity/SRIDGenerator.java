package edu.wpi.cs3733.c22.teamB.entity;

public class SRIDGenerator {
    public static String generateID() {
        DatabaseWrapper dw = new DatabaseWrapper();
        int i = 0;
        String ret;
        while (dw.getSR(ret = ("SR" + i)) != null)
            ++i;
        return ret;
    }
}
