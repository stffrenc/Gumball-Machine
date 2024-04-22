package edu.iu.stffrenc.GumballMachine.model;


public class SoldState implements IState{
    IGumballMachine gumballMachine;
    public SoldState(IGumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public TransitionResult insertQuarter() {
        String message = "Can't enter quarter while dispensing gumball";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult ejectQuarter() {
        String message = "You can't eject quarter when already sold";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult turnCrank() {
        String message = "Crank has already been turned";
        boolean succeeded = false;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
    @Override
    public TransitionResult dispense() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "Gumball dispensed";
        boolean succeeded = true;
        int count = gumballMachine.getCount() - 1;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), count);

    }
    @Override
    public String getTheName() {
        return GumballMachineState.GUMBALL_SOLD.name();
    }

    @Override
    public TransitionResult refill() {
        gumballMachine.changeTheStateTo(GumballMachineState.NO_QUARTER);
        String message = "Gumball Machine is refilled with gumballs.";
        boolean succeeded = true;
        return new TransitionResult(succeeded, message, gumballMachine.getTheStateName(), gumballMachine.getCount());
    }
}



