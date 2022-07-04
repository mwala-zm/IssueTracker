package com.zechariah.issuetrackerdal.controller;

import com.zechariah.issuetrackerdal.model.State;
import com.zechariah.issuetrackerdal.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {

    @Autowired
    StateRepository repository;

    public StateController(StateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/state")
    public List<State> all(){
        return repository.findAll();
    }

    @PostMapping(value = "/state")
    State newState(@RequestBody State state){
        return  repository.save(state);
    }

    @DeleteMapping(value = "/state/{id}")
    void removeState(@PathVariable Long id){
        repository.deleteById(id);
    }

    //Updating State information
    @PutMapping(value = "/state/{id}")
    public State updateState(@RequestBody State state, @PathVariable Long id){

        return repository.findById(id)
                .map(infor -> {
                    infor.setDescription(state.getDescription());
                    infor.setName(state.getName());
                    return repository.save(infor);
                })
                .orElseGet(() -> {
                   state.setId(id);
                   return repository.save(state);
                });
    }
}
