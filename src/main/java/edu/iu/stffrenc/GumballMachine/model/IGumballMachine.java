package edu.iu.stffrenc.GumballMachine.model;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    String getTheStateName();

    TransitionResult releaseBall();
}
