package com.indigo.bankservice.services;

import com.indigo.bankservice.models.BankAccount;
import com.indigo.bankservice.repositories.BankRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankRepository repo;

    @PersistenceContext
    private EntityManager entityManager;


    public List<BankAccount> findAll(){
        return this.repo.findAll();
    }


    public BankAccount findById(String ccn){
        return repo.findById(ccn).orElse(null);
    }


    public void save(BankAccount account){
        repo.save(account);
    }


    @Transactional
    public BankAccount increaseBalance(String ccn, double amount) throws Exception {
          BankAccount account = repo.findById(ccn).orElse(null);
          if(account != null){
              entityManager.refresh(account);
              account.setBalance(account.getBalance()+amount);
              repo.save(account);
              return account;
          }
          else{
              throw new Exception("Cannot find account");
          }
    }


    public BankAccount deductFromBalance(String creditCardNumber, double amount) throws Exception {
        BankAccount account = repo.findById(creditCardNumber).orElse(null);
        if(account != null && account.getBalance() >= amount){
            account.setBalance(account.getBalance()-amount);
            repo.save(account);
            return account;
        }
        else{
            throw new Exception("Cannot find account or not enough balance");
        }

    }
}
