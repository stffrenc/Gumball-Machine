package edu.iu.stffrenc.GumballMachine.service;

import edu.iu.stffrenc.GumballMachine.model.GumballMachine;
import edu.iu.stffrenc.GumballMachine.model.GumballMachineRecord;
import edu.iu.stffrenc.GumballMachine.model.IGumballMachine;
import edu.iu.stffrenc.GumballMachine.model.TransitionResult;
import edu.iu.stffrenc.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.io.IOException;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = machine.insertQuarter();
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = machine.ejectQuarter();
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException{
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = machine.turnCrank();
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        dispense(id);
        return result;
    }


    @Override
    public TransitionResult dispense(String id) throws IOException{
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = machine.releaseBall();
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}
