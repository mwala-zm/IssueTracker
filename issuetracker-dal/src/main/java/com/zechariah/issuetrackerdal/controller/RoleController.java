package com.zechariah.issuetrackerdal.controller;

import com.zechariah.issuetrackerdal.model.Role;
import com.zechariah.issuetrackerdal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public List<Role> all(){
        return roleRepository.findAll();
    }

    @PostMapping(value = "/role")
    public Role newRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @PutMapping(value = "/role")
    public Role updateRole(@RequestBody Role role, @PathVariable Long id){
        return roleRepository.findById(id)
                .map(information -> {
                    information.setAuthority(role.getAuthority());
                    return roleRepository.save(information);
                })
                .orElseGet(() -> {
                   role.setId(id);
                   return roleRepository.save(role);
                });
    }
}
