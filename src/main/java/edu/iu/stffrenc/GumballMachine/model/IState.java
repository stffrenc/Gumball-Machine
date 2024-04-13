package edu.iu.stffrenc.GumballMachine.model;

public interface IState {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult dispense();
    String getTheName();
}
