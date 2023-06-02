package com.indigo.bankservice.controllers;

import com.indigo.bankservice.models.BankAccount;
import com.indigo.bankservice.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService service;
    @Autowired
    public BankController(BankService service){
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<BankAccount> findAll(){
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{ccn}")
    public BankAccount findById(@PathVariable(value="ccn") String ccn){
        return service.findById(ccn);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody BankAccount account){
        service.save(account);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{ccn}")
    public BankAccount deductFromBalance(@PathVariable(value="ccn") String ccn, @RequestBody double amount){
        try {
            return service.deductFromBalance(ccn, amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }





}
