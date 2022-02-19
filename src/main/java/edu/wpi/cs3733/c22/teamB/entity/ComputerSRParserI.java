package edu.wpi.cs3733.c22.teamB.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerSRParserI implements IParser<ComputerSR> {

    @Override
    public ComputerSR fromStringToObject(String string) {

        ComputerSR computerServiceSR = new ComputerSR();

        String[] data = string.split(",");

        computerServiceSR.setSrID(data[0]);
        computerServiceSR.setHelpType(data[1]);

        return computerServiceSR;
    }

    @Override
    public List<ComputerSR> fromStringsToObjects(List<String> listString) {

        List<ComputerSR> computerServiceSRList =
                listString.stream()
                        .map(
                                data_str -> {
                                    return fromStringToObject(data_str);
                                })
                        .collect(Collectors.toList());

        return computerServiceSRList;
    }

    @Override
    public String fromObjectToString(ComputerSR computerServiceSR) {

        String str = computerServiceSR.toStringFields();

        return str;
    }

    @Override
    public List<String> fromObjectsToStrings(List<ComputerSR> listT) {

        List<String> listString = new ArrayList<>();
        listString.add(ComputerSR.toStringHeader());
        for (ComputerSR computerServiceSR : listT) {
            listString.add(computerServiceSR.toStringFields());
        }

        return listString;
    }
}
