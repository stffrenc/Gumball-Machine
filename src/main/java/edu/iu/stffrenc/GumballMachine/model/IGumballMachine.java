package edu.iu.stffrenc.GumballMachine.model;

import java.io.IOException;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    String getTheStateName();

    TransitionResult refill() throws IOException;


    TransitionResult releaseBall();
}
