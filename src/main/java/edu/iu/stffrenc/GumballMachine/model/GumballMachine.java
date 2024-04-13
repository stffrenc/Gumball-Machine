package edu.iu.stffrenc.GumballMachine.model;

public class GumballMachine implements IGumballMachine {
    final String SOLD_OUT = GumballMachineState.OUT_OF_GUMBALLS.name();
    final String NO_QUARTER = GumballMachineState.NO_QUARTER.name();
    final String HAS_QUARTER = GumballMachineState.HAS_QUARTER.name();
    final String SOLD = GumballMachineState.GUMBALL_SOLD.name();
    private String id;
    String state = SOLD_OUT;
    int count = 0;

    public GumballMachine(String id, String state, int count) {
        this.id = id;
        this.state = state;
        this.count = count;
    }

    private TransitionResult handleAction(boolean canProceed, String successState, String successMessage) {
        if (state.equalsIgnoreCase(SOLD_OUT)) {
            return new TransitionResult(false, "Machine is sold out", state, count);
        } else if (state.equalsIgnoreCase(SOLD)) {
            return new TransitionResult(false, "Please wait, we're already giving you a gumball", state, count);
        } else if (canProceed) {
            state = successState;
            return new TransitionResult(true, successMessage, state, count);
        } else {
            String message = "Invalid action";
            if (state.equalsIgnoreCase(NO_QUARTER)) {
                message = "There is no quarter";
            } else if (state.equalsIgnoreCase(HAS_QUARTER)) {
                message = "Quarter already inserted";
            }
            return new TransitionResult(false, message, state, count);
        }
    }

    @Override
    public TransitionResult insertQuarter() {
        return handleAction(state.equalsIgnoreCase(NO_QUARTER), HAS_QUARTER, "You inserted a quarter");
    }

    @Override
    public TransitionResult ejectQuarter() {
        return handleAction(state.equalsIgnoreCase(HAS_QUARTER), NO_QUARTER, "You ejected a quarter");
    }

    @Override
    public TransitionResult turnCrank() {
        return handleAction(state.equalsIgnoreCase(HAS_QUARTER), SOLD, "You turned the crank");
    }

    @Override
    public void changeTheStateTo(GumballMachineState name) {
        state = name.name();
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String getTheStateName() {
        return null;
    }

    @Override
    public TransitionResult releaseBall() {
        if (state.equalsIgnoreCase(SOLD)) {
            state = NO_QUARTER;
            count--;
            return new TransitionResult(true, "Dispensing gumball", state, count);
        } else {
            return new TransitionResult(false, "You didn't turn the crank", state, count);
        }
    }
}