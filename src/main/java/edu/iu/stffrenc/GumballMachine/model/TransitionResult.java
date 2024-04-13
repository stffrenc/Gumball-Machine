package edu.iu.stffrenc.GumballMachine.model;

public record TransitionResult(boolean succeeded, String message, String stateAfter, Integer countAfter) {
}
