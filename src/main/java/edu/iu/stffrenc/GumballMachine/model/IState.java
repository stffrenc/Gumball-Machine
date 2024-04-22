package edu.iu.stffrenc.GumballMachine.model;

public interface IState {
    TransitionResult refill();
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult dispense();
    String getTheName();
}
