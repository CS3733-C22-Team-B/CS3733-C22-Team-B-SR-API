package edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.controllers;

import edu.wpi.cs3733.c22.teamB.ServiceRequestAPI.entity.AbstractSR;

public interface IController {

    void submit();
    void submit(AbstractSR sr);

    void clear();
}
